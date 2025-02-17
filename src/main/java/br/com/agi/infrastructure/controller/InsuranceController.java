package br.com.agi.infrastructure.controller;

import br.com.agi.application.service.InsuranceService;
import br.com.agi.domain.model.Insurance;
import br.com.agi.infrastructure.request.InsuranceContractRequest;
import br.com.agi.infrastructure.response.InsuranceSimulationResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurances")
public class InsuranceController {

    @Autowired
    private final InsuranceService service;

    public InsuranceController(InsuranceService service) {
        this.service = service;
    }

    @PostMapping("/contract")
    public ResponseEntity<Insurance> contract(@NotNull @RequestBody InsuranceContractRequest insuranceContractRequest) {
        return ResponseEntity.ok(service.createInsurance(insuranceContractRequest.getCpf(), insuranceContractRequest.getType()));
    }

    @GetMapping("/simulate/{cpf}")
    public ResponseEntity<InsuranceSimulationResponse> simulateInsurance(@PathVariable("cpf") String cpf) {
        try {
            InsuranceSimulationResponse response = service.simulateInsurance(cpf);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
