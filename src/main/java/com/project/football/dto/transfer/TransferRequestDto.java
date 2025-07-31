package com.project.football.dto.transfer;

import jakarta.validation.constraints.NotNull;

public record TransferRequestDto(@NotNull
                                 Long playerId,
                                 @NotNull
                                 Long teamId) {
}
