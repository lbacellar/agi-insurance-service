package br.com.agi.domain.port.in;

import br.com.agi.domain.model.Insurance;
import br.com.agi.infrastructure.response.InsuranceSimulationResponse;

public interface InsuranceUseCase {
    InsuranceSimulationResponse simulateInsurance(String cpf);
    Insurance createInsurance(String cpf, String type);
}
