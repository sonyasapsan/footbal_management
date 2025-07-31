package com.project.football.mapper;

import com.project.football.config.MapperConfig;
import com.project.football.dto.player.TeamPlayerResponseDto;
import com.project.football.dto.team.TeamResponseDto;
import com.project.football.model.Player;
import com.project.football.model.Team;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface TeamMapper {
    TeamResponseDto toDto(Team team);

    TeamPlayerResponseDto toPlayerDto(Player player);
}
