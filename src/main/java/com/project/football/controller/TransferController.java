package com.project.football.controller;

import com.project.football.dto.transfer.TransferRequestDto;
import com.project.football.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transfers")
@Validated
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void transferPlayer(@RequestBody @Valid TransferRequestDto requestDto) {
        transferService.doTransfer(requestDto);
    }
}
