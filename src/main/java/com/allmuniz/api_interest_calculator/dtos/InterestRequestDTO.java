package com.allmuniz.api_interest_calculator.dtos;

import com.allmuniz.api_interest_calculator.enums.InterestType;

public record InterestRequestDTO(
        Double capital,
        Double interestRate,
        Integer time,
        InterestType type
) {
}
