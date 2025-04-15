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

    public ResponseEntity<InterestResponseDTO> calcularJuros(InterestRequestDTO request){

        var type = InterestType.fromId(request.type());
        double capital = request.capital();
        double porcentagemJuros = request.interestRate() / 100;

        int tempo = request.typeTime() == 1 ? request.time() : request.time() * 12;

        double montante;
        double juros;

        if (type == InterestType.SIMPLE){
            juros =  capital * porcentagemJuros * tempo;
            montante = capital + juros;
        } else {
            montante = capital * Math.pow(1 + porcentagemJuros, tempo);
            juros = montante - capital;
        }

        BigDecimal montanteFormatted = BigDecimal.valueOf(montante).setScale(2, RoundingMode.HALF_UP);
        BigDecimal jurosFormatted = BigDecimal.valueOf(juros).setScale(2, RoundingMode.HALF_UP);


        return ResponseEntity.ok(new InterestResponseDTO(montanteFormatted.doubleValue(), jurosFormatted.doubleValue()));
    }
}
