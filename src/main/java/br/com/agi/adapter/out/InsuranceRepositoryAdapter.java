package br.com.agi.adapter.out;

import br.com.agi.domain.model.Insurance;
import br.com.agi.application.port.out.InsuranceRepositoryPort;
import br.com.agi.adapter.out.repository.MongoInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsuranceRepositoryAdapter implements InsuranceRepositoryPort {

    @Autowired
    private final MongoInsuranceRepository repository;

    public InsuranceRepositoryAdapter(MongoInsuranceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Insurance save(Insurance insurance) {
        return repository.save(insurance);
    }
}

