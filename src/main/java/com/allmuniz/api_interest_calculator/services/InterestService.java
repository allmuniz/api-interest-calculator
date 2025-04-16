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
        InterestType type = InterestType.fromId(request.type());

        BigDecimal capital = BigDecimal.valueOf(request.capital());
        BigDecimal monthlyValue = BigDecimal.valueOf(request.monthlyValue());
        BigDecimal taxaJurosAnual = BigDecimal.valueOf(request.interestRate()).divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
        int tempoAnos = request.time();
        int meses = tempoAnos * 12;

        BigDecimal montante;
        BigDecimal juros;

        if (type == InterestType.SIMPLE) {
            // Juros simples: J = C * i * t + AporteMensal * ((t * 12 + 1) / 2) * (i / 12)
            BigDecimal jurosCapital = capital.multiply(taxaJurosAnual).multiply(BigDecimal.valueOf(tempoAnos));

            BigDecimal mediaMeses = BigDecimal.valueOf(meses + 1).divide(BigDecimal.valueOf(2), 10, RoundingMode.HALF_UP);
            BigDecimal jurosAportes = monthlyValue
                    .multiply(mediaMeses)
                    .multiply(taxaJurosAnual.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP));

            juros = jurosCapital.add(jurosAportes);
            montante = capital.add(monthlyValue.multiply(BigDecimal.valueOf(meses))).add(juros);

        } else {
            // Juros compostos com aportes mensais:
            // M = C*(1+i)^t + PMT*[(1+i)^n - 1]/i
            BigDecimal taxaMensal = taxaJurosAnual.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
            BigDecimal fator = BigDecimal.ONE.add(taxaMensal).pow(meses);

            BigDecimal montanteCapital = capital.multiply(fator);
            BigDecimal montanteAportes = monthlyValue.multiply(fator.subtract(BigDecimal.ONE)).divide(taxaMensal, 10, RoundingMode.HALF_UP);

            montante = montanteCapital.add(montanteAportes);
            BigDecimal valorInvestido = capital.add(monthlyValue.multiply(BigDecimal.valueOf(meses)));
            juros = montante.subtract(valorInvestido);
        }

        BigDecimal montanteFinal = montante.setScale(2, RoundingMode.HALF_UP);
        BigDecimal jurosFinal = juros.setScale(2, RoundingMode.HALF_UP);
        BigDecimal valorInvestidoFinal = capital.add(monthlyValue.multiply(BigDecimal.valueOf(meses))).setScale(2, RoundingMode.HALF_UP);

        return ResponseEntity.ok(
                new InterestResponseDTO(
                        montanteFinal.doubleValue(),
                        jurosFinal.doubleValue(),
                        valorInvestidoFinal.doubleValue()
                )
        );
    }
}
