package br.com.agi.adapter.out;

import br.com.agi.application.exception.ApiServiceUnavailableException;
import br.com.agi.application.port.out.CustomerValidationPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerValidationAdapter implements CustomerValidationPort {

    private final Logger logger = LoggerFactory.getLogger(CustomerValidationAdapter.class);
    private final RestTemplate restTemplate;
    private static final String CUSTOMER_API_URL = "http://localhost:8080/customers/";

    public CustomerValidationAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retry(name = "customerValidationRetry", fallbackMethod = "fallbackCustomerValidation")
    @CircuitBreaker(name = "customerValidationCircuitBreaker", fallbackMethod = "fallbackCustomerValidation")
    public boolean isCustomerRegistered(String cpf) {
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(CUSTOMER_API_URL + cpf, Void.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    private boolean fallbackCustomerValidation(String cpf, Throwable t) {
        System.err.println("Fallback: " + t.getMessage());
        logger.error(t.getMessage());
        throw new ApiServiceUnavailableException(HttpStatus.SERVICE_UNAVAILABLE, "Customer API unavailability");
    }
}
