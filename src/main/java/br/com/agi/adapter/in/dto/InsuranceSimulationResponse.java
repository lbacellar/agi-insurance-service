package br.com.agi.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InsuranceSimulationResponse {
    private String cpf;
    private double bronzePrice;
    private double silverPrice;
    private double goldPrice;
}
