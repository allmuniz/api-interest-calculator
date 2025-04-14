package com.allmuniz.api_interest_calculator.services;

import com.allmuniz.api_interest_calculator.dtos.InterestRequestDTO;
import com.allmuniz.api_interest_calculator.dtos.InterestResponseDTO;
import com.allmuniz.api_interest_calculator.enums.InterestType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InterestService {

    public ResponseEntity<InterestResponseDTO> calcularJuros(InterestRequestDTO request){

        var type = InterestType.fromId(request.type());
        double c = request.capital();
        double i = request.interestRate() / 100;

        int t = request.typeTime() == 1 ? request.time() : request.time() * 12;

        double M;
        double J;

        if (type == InterestType.SIMPLE){
            J =  c * i * t;
            M = c + J;
        } else {
            M = c * Math.pow(1 + i, t);
            J = M - c;
        }
        return ResponseEntity.ok(new InterestResponseDTO(M, J));
    }
}
