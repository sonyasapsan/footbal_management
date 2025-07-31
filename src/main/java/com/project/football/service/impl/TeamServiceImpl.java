package com.project.football.service.impl;

import com.project.football.dto.team.CreateOrUpdateTeamRequestDto;
import com.project.football.dto.team.TeamResponseDto;
import com.project.football.exception.TeamNotFoundException;
import com.project.football.mapper.TeamMapper;
import com.project.football.model.Team;
import com.project.football.repository.TeamRepository;
import com.project.football.service.TeamService;
import com.project.football.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private  final TeamRepository repository;
    private final TeamMapper mapper;
    @Override
    public TeamResponseDto getById(Long id) {
        return mapper.toDto(getTeamOrThrow(id));
    }

    @Override
    public Page<TeamResponseDto> getAll(Pageable pageable) {
        Page<Team> teams = repository.findAll(pageable);
        return teams.map(mapper::toDto);
    }

    @Override
    public TeamResponseDto addTeam(CreateOrUpdateTeamRequestDto request) {
        Team team = new Team();
        team.setBalance(request.balance());
        team.setName(request.name());
        team.setCommission(request.commission());
        return mapper.toDto(repository.save(team));
    }

    @Override
    public void deleteById(Long id) {
        getTeamOrThrow(id); //checking if the team is in db
        repository.deleteById(id);
    }

    @Override
    public TeamResponseDto updateById(Long id, CreateOrUpdateTeamRequestDto request) {
        Team team = getTeamOrThrow(id);
        team.setName(request.name());
        team.setBalance(request.balance());
        team.setCommission(request.commission());
        return mapper.toDto(repository.save(team));
    }

    @Override
    public Team getTeamOrThrow(Long id) { //package-private so another services could invoke this method
        return repository.findById(id).orElseThrow(
                () -> new TeamNotFoundException(Constants.Team.TEAM_NOT_FOUND + id)
        );
    }
}
