package com.project.football.mapper;

import com.project.football.config.MapperConfig;
import com.project.football.dto.player.CreatePlayerRequestDto;
import com.project.football.dto.player.PlayerResponseDto;
import com.project.football.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PlayerMapper {
    @Mapping(source = "team.name", target = "teamName")
    PlayerResponseDto toDto(Player player);

    @Mapping(target = "team", ignore = true)
    Player toEntity(CreatePlayerRequestDto dto);
}
