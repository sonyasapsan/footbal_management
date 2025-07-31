package com.project.football.service.impl;

import com.project.football.dto.transfer.TransferRequestDto;
import com.project.football.exception.TransferException;
import com.project.football.model.Player;
import com.project.football.model.Team;
import com.project.football.model.Transfer;
import com.project.football.repository.PlayerRepository;
import com.project.football.repository.TeamRepository;
import com.project.football.repository.TransferRepository;
import com.project.football.service.PlayerService;
import com.project.football.service.TeamService;
import com.project.football.service.TransferService;
import com.project.football.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    @Value("${transfer.base-multiplier}")
    private BigDecimal baseMultiplier;
    private final PlayerService playerService;
    private final TeamService teamService;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Override
    @Transactional
    public void doTransfer(TransferRequestDto requestDto) {
        Player player = playerService.getPlayerOrThrow(requestDto.playerId());
        Team fromTeam = player.getTeam();
        Team toTeam = teamService.getTeamOrThrow(requestDto.teamId());
        if (fromTeam.getId().equals(toTeam.getId())) {
            throw new TransferException(Constants.Transfer.TEAM_ALREADY_HAVE_PLAYER + player.getId());
        }

        BigDecimal transferAmount = getTransferAmount(player);

        BigDecimal commission = calculateCommission(toTeam, transferAmount);
        BigDecimal totalCost = transferAmount.add(commission);

        if (toTeam.getBalance().compareTo(totalCost) < 0) {
            throw new TransferException(Constants.Transfer.NOT_ENOUGH_MONEY);
        }
        toTeam.setBalance(toTeam.getBalance().subtract(totalCost));
        fromTeam.setBalance(fromTeam.getBalance().add(totalCost));
        player.setTeam(toTeam);
        playerRepository.save(player);
        teamRepository.save(fromTeam);
        teamRepository.save(toTeam);
        saveTransfer(player, fromTeam, toTeam, commission, totalCost, transferAmount);

    }

    private void saveTransfer(Player player, Team fromTeam, Team toTeam,
                              BigDecimal commission, BigDecimal totalCost,  BigDecimal transferAmount) {
        Transfer transfer = new Transfer();
        transfer.setPlayer(player);
        transfer.setFromTeam(fromTeam);
        transfer.setToTeam(toTeam);
        transfer.setCommission(commission);
        transfer.setTransferAmount(transferAmount);
        transfer.setTotalCost(totalCost);
        transferRepository.save(transfer);
    }

    private BigDecimal getTransferAmount(Player player) {
        BigDecimal experience = BigDecimal.valueOf(player.getExperienceMonths());
        BigDecimal age = BigDecimal.valueOf(player.getAge());
        return experience.multiply(baseMultiplier).divide(age, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateCommission(Team team, BigDecimal transferAmount) {
        return transferAmount
                .multiply(BigDecimal.valueOf(team.getCommission()))
                .divide(BigDecimal.valueOf(100));
    }
}
