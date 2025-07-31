package com.project.football.dto.player;

public record PlayerResponseDto(String fullName,
                                Integer age,
                                Integer experienceMonths,
                                String teamName) {
}
