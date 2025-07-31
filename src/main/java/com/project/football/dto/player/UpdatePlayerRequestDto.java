package com.project.football.dto.player;

public record UpdatePlayerRequestDto(String fullName,
                                     Integer age,
                                     Integer experienceMonths) {
}
