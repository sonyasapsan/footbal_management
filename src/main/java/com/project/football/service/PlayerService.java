package com.project.football.service;

import com.project.football.dto.player.CreatePlayerRequestDto;
import com.project.football.dto.player.PlayerResponseDto;
import com.project.football.dto.player.UpdatePlayerRequestDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {
    Page<PlayerResponseDto> getAll(Pageable pageable);

    PlayerResponseDto getById(Long id);

    PlayerResponseDto addPlayer(CreatePlayerRequestDto request);

    void deleteById(Long id);

    PlayerResponseDto updateById(Long id, UpdatePlayerRequestDto request);
}
