package br.com.agi.application.port.out;

public interface CustomerValidationPort {
    boolean isCustomerRegistered(String cpf);
}
