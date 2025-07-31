package com.project.football.dto.player;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePlayerRequestDto(
    @NotBlank
    String fullName,
    @NotNull
    @Min(value = 0)
    Integer age,
    @NotNull
    @Min(value = 0)
    Integer experienceMonths,
    @NotNull
    @Min(value = 0)
    Long teamId
) {}