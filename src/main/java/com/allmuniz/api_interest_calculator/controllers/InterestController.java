package com.allmuniz.api_interest_calculator.controllers;

import com.allmuniz.api_interest_calculator.dtos.InterestRequestDTO;
import com.allmuniz.api_interest_calculator.dtos.InterestResponseDTO;
import com.allmuniz.api_interest_calculator.services.InterestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/interest")
@Tag(name = "Interest", description = "Interest Manager")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class InterestController {

    private final InterestService service;

    @PostMapping("/")
    @Operation(description = "Endpoint responsavel por fazer o calculo do juros",
            summary = "Calculo de juros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculo efetuado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<InterestResponseDTO> calculator(@RequestBody InterestRequestDTO request){
        return service.calcularJuros(request);
    }
}
