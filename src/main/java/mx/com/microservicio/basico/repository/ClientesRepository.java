package mx.com.microservicio.basico.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;

import mx.com.microservicio.basico.entity.ClientesEntity;

public interface ClientesRepository extends CosmosRepository<ClientesEntity, String>{

}
