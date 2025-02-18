package br.com.agi.application.service;

import br.com.agi.application.exception.CustomerNotFoundException;
import br.com.agi.domain.model.Insurance;
import br.com.agi.application.port.in.InsuranceUseCase;
import br.com.agi.application.port.out.CustomerValidationPort;
import br.com.agi.application.port.out.InsuranceRepositoryPort;
import br.com.agi.adapter.in.dto.InsuranceSimulationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService implements InsuranceUseCase {

    public final String GOLD = "GOLD";
    public final String SILVER = "SILVER";
    public final String BRONZE = "BRONZE";
    public final String CUSTOMER_NOT_FOUND = "Customer not found in [agi-customer-service]";

    @Autowired
    private final InsuranceRepositoryPort repository;
    @Autowired
    private final CustomerValidationPort customerValidation;

    public InsuranceService(InsuranceRepositoryPort repository, CustomerValidationPort customerValidation) {
        this.repository = repository;
        this.customerValidation = customerValidation;
    }

    @Override
    public Insurance createInsurance(String cpf, String type) {

        if (!customerValidation.isCustomerRegistered(cpf)) {
            throw new CustomerNotFoundException(HttpStatus.NOT_FOUND, CUSTOMER_NOT_FOUND);
        }

        double price = calculatePrice(type);
        Insurance insurance = new Insurance(null, cpf, type, price);
        return repository.save(insurance);
    }

    @Override
    public InsuranceSimulationResponse simulateInsurance(String cpf) {
        if (!customerValidation.isCustomerRegistered(cpf)) {
            throw new CustomerNotFoundException(HttpStatus.NOT_FOUND, CUSTOMER_NOT_FOUND);
        }

        return new InsuranceSimulationResponse(cpf, calculatePrice(BRONZE), calculatePrice(SILVER), calculatePrice(GOLD));
    }

    private double calculatePrice(String type) {
        return switch (type.toUpperCase()) {
            case BRONZE -> 300.0;
            case SILVER -> 600.0;
            case GOLD -> 900.0;
            default -> throw new IllegalArgumentException("Invalid insurance type");
        };
    }
}
