package br.com.agi.domain.port.out;

import br.com.agi.domain.model.Insurance;

public interface InsuranceRepositoryPort {
    Insurance save(Insurance insurance);
}
