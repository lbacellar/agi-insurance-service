package br.com.agi.domain.port.out;

public interface CustomerValidationPort {
    boolean isCustomerRegistered(String cpf);
}
