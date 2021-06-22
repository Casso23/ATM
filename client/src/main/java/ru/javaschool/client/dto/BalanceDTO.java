package ru.javaschool.client.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class BalanceDTO {
    private final BigDecimal balance;
}
