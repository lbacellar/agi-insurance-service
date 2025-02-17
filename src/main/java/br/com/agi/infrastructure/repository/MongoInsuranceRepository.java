package br.com.agi.infrastructure.repository;

import br.com.agi.domain.model.Insurance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoInsuranceRepository extends MongoRepository<Insurance, String> { }
