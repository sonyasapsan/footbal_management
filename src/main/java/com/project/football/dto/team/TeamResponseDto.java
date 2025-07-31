package com.project.football.dto.team;

import com.project.football.dto.player.TeamPlayerResponseDto;

import java.math.BigDecimal;
import java.util.Set;

public record TeamResponseDto(String name,
                              Float commission,
                              BigDecimal balance,
                              Set<TeamPlayerResponseDto> players) {
}
