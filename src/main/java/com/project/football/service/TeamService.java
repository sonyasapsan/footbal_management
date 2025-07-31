package com.project.football.service;

import com.project.football.dto.team.CreateOrUpdateTeamRequestDto;
import com.project.football.dto.team.TeamResponseDto;
import com.project.football.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamService {
    TeamResponseDto getById(Long id);

    Page<TeamResponseDto> getAll(Pageable pageable);

    TeamResponseDto addTeam(CreateOrUpdateTeamRequestDto request);

    void deleteById(Long id);

    TeamResponseDto updateById(Long id, CreateOrUpdateTeamRequestDto request);

    Team getTeamOrThrow(Long id);
}
