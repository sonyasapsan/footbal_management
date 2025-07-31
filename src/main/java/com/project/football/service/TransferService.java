package com.project.football.service;

import com.project.football.dto.transfer.TransferRequestDto;

public interface TransferService {
    void doTransfer(TransferRequestDto requestDto);
}
