package com.allmuniz.api_interest_calculator.dtos;

public record InterestRequestDTO(
        Double capital,
        Double monthlyValue,
        Double interestRate,
        Integer time,
        Integer type
) {
}
