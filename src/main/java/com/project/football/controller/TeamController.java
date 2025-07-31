package com.project.football.controller;

import com.project.football.dto.team.CreateOrUpdateTeamRequestDto;
import com.project.football.dto.team.TeamResponseDto;
import com.project.football.service.TeamService;
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
@RequestMapping("/teams")
@Validated
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    public Page<TeamResponseDto> getAll(Pageable pageable) {
        return teamService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public TeamResponseDto getById(@PathVariable @Min(1) Long id) {
        return teamService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDto addTeam(@Valid @RequestBody CreateOrUpdateTeamRequestDto request) {
        return teamService.addTeam(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Min(1) Long id) {
        teamService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponseDto> TeamPlayer(@PathVariable @Min(1) Long id,
                                                          @Valid @RequestBody CreateOrUpdateTeamRequestDto request) {
        return ResponseEntity.ok(teamService.updateById(id, request));
    }
}
