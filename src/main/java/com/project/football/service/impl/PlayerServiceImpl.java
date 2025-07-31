package com.project.football.service.impl;

import com.project.football.dto.player.CreatePlayerRequestDto;
import com.project.football.dto.player.PlayerResponseDto;
import com.project.football.dto.player.UpdatePlayerRequestDto;
import com.project.football.exception.PlayerNotFoundException;
import com.project.football.exception.TeamNotFoundException;
import com.project.football.mapper.PlayerMapper;
import com.project.football.model.Player;
import com.project.football.model.Team;
import com.project.football.repository.PlayerRepository;
import com.project.football.repository.TeamRepository;
import com.project.football.service.PlayerService;
import com.project.football.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository repository;
    private final PlayerMapper mapper;
    private final TeamRepository teamRepository;
    @Override
    public Page<PlayerResponseDto> getAll(Pageable pageable) {
        Page<Player> players = repository.findAll(pageable);
        return players.map(mapper::toDto);
    }

    @Override
    public PlayerResponseDto getById(Long id) {
        return mapper.toDto(getPlayerOrThrow(id));
    }

    @Override
    public PlayerResponseDto addPlayer(CreatePlayerRequestDto request) {
        Team team = teamRepository.findById(request.teamId()).orElseThrow(
                () -> new TeamNotFoundException(Constants.Team.TEAM_NOT_FOUND + request.teamId())
        );
        Player player = mapper.toEntity(request);
        player.setTeam(team);
        return mapper.toDto(repository.save(player));
    }

    @Override
    public void deleteById(Long id) {
        getPlayerOrThrow(id); //checking if the player is in db
        repository.deleteById(id);
    }

    @Override
    public PlayerResponseDto updateById(Long id, UpdatePlayerRequestDto request) {
        Player player = getPlayerOrThrow(id);
        player.setFullName(request.fullName());
        player.setAge(request.age());
        player.setExperienceMonths(request.experienceMonths());
        return mapper.toDto(repository.save(player));
    }

    private Player getPlayerOrThrow(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new PlayerNotFoundException(Constants.Player.PLAYER_NOT_FOUND + id)
        );
    }
}
