package mx.com.microservicio.basico.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;

import mx.com.microservicio.basico.entity.SequenciaEntity;

public interface SequenciaRepository extends CosmosRepository<SequenciaEntity, String> {

}
