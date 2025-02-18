package br.com.agi.application.port.in;

import br.com.agi.domain.model.Insurance;
import br.com.agi.adapter.in.dto.InsuranceSimulationResponse;

public interface InsuranceUseCase {
    InsuranceSimulationResponse simulateInsurance(String cpf);
    Insurance createInsurance(String cpf, String type);
}
