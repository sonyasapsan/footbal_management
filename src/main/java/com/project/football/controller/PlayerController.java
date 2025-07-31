package com.project.football.controller;

import com.project.football.dto.player.CreatePlayerRequestDto;
import com.project.football.dto.player.PlayerResponseDto;
import com.project.football.dto.player.UpdatePlayerRequestDto;
import com.project.football.service.PlayerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/players")
@Validated
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping
    public Page<PlayerResponseDto> getAll(Pageable pageable) {
        return playerService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public PlayerResponseDto getById(@PathVariable @Min(1) Long id) {
        return playerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerResponseDto addPlayer(@Valid @RequestBody CreatePlayerRequestDto request) {
        return playerService.addPlayer(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Min(1) Long id) {
        playerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> updatePlayer(@PathVariable @Min(1) Long id,
                                          @Valid @RequestBody UpdatePlayerRequestDto request) {
        return ResponseEntity.ok(playerService.updateById(id, request));
    }
}
