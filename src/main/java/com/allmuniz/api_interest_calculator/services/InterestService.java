package com.allmuniz.api_interest_calculator.services;

import com.allmuniz.api_interest_calculator.dtos.InterestRequestDTO;
import com.allmuniz.api_interest_calculator.dtos.InterestResponseDTO;
import com.allmuniz.api_interest_calculator.enums.InterestType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class InterestService {

    public ResponseEntity<InterestResponseDTO> calcularJuros(InterestRequestDTO request) {
        // Validando tipo
        InterestType type = InterestType.fromId(request.type());
        if (type == null) {
            return ResponseEntity.badRequest().build(); // ou lançar uma exceção customizada
        }

        // Conversão de dados de entrada para BigDecimal
        BigDecimal capital = BigDecimal.valueOf(request.capital());
        BigDecimal taxaJurosAnual = BigDecimal.valueOf(request.interestRate()).divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
        int tempoAnos = request.time();

        BigDecimal montante;
        BigDecimal juros;

        if (type == InterestType.SIMPLE) {
            // Juros Simples: J = C * i * t ; M = C + J
            System.out.println("Entrou no simples");
            juros = capital.multiply(taxaJurosAnual).multiply(BigDecimal.valueOf(tempoAnos));
            montante = capital.add(juros);
        } else {
            // Juros Compostos: M = C * (1 + i)^t ; J = M - C
            System.out.println("Entrou no composto");
            BigDecimal base = BigDecimal.ONE.add(taxaJurosAnual);
            BigDecimal fator = base.pow(tempoAnos);
            montante = capital.multiply(fator);
            juros = montante.subtract(capital);
        }

        // Arredondando os valores para 2 casas decimais
        BigDecimal montanteFinal = montante.setScale(2, RoundingMode.HALF_UP);
        BigDecimal jurosFinal = juros.setScale(2, RoundingMode.HALF_UP);

        return ResponseEntity.ok(new InterestResponseDTO(montanteFinal.doubleValue(), jurosFinal.doubleValue()));
    }

}
