package br.com.agi.application.service;

import br.com.agi.application.exception.CustomerNotFoundException;
import br.com.agi.domain.model.Insurance;
import br.com.agi.domain.port.out.CustomerValidationPort;
import br.com.agi.domain.port.out.InsuranceRepositoryPort;
import br.com.agi.infrastructure.response.InsuranceSimulationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InsuranceServiceTest {

    @Mock
    private InsuranceRepositoryPort repository;

    @Mock
    private CustomerValidationPort customerValidation;

    @InjectMocks
    private InsuranceService insuranceService;

    @BeforeEach
    void setUp() {
        insuranceService = new InsuranceService(repository, customerValidation);
    }

    @Test
    void shouldCreateInsuranceSuccessfully() {
        String cpf = "12345678900";
        String type = "Gold";

        when(customerValidation.isCustomerRegistered(cpf)).thenReturn(true);
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Insurance insurance = insuranceService.createInsurance(cpf, type);

        assertNotNull(insurance);
        assertEquals(cpf, insurance.getCustomerCpf());
        assertEquals(type, insurance.getType());
        assertEquals(900.0, insurance.getPrice());
        verify(repository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        String cpf = "99999999999";
        String type = "Silver";

        when(customerValidation.isCustomerRegistered(cpf)).thenReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> insuranceService.createInsurance(cpf, type));
        verify(repository, never()).save(any());
    }

    @Test
    void shouldSimulateInsuranceSuccessfully() {
        String cpf = "12345678900";

        when(customerValidation.isCustomerRegistered(cpf)).thenReturn(true);

        InsuranceSimulationResponse response = insuranceService.simulateInsurance(cpf);

        assertNotNull(response);
        assertEquals(300.0, response.getBronzePrice());
        assertEquals(600.0, response.getSilverPrice());
        assertEquals(900.0, response.getGoldPrice());
    }
}
