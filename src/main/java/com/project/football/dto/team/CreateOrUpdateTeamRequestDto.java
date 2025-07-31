package com.project.football.dto.team;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateOrUpdateTeamRequestDto(@NotBlank
                                   String name,
                                           @NotNull
                                   @Min(value = 0)
                                   Float commission,
                                           @NotNull
                                   @Min(value = 0)
                                   BigDecimal balance
                                   ) {
}
