package mx.com.microservicio.basico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.microservicio.basico.dto.MicroservicioCrudBasicoRequestDTO;
import mx.com.microservicio.basico.dto.MicroservicioCrudBasicoResponseDTO;
import mx.com.microservicio.basico.entity.ClientesEntity;
import mx.com.microservicio.basico.entity.SequenciaEntity;
import mx.com.microservicio.basico.repository.ClientesRepository;
import mx.com.microservicio.basico.repository.SequenciaRepository;

@Service
public class MicroservicioCrudBasicoService {

	@Autowired
	public ClientesRepository clientesRepository;

	@Autowired
	public SequenciaRepository sequenciaRepository;
	
	private Long secuencia = 0L;

	public MicroservicioCrudBasicoResponseDTO registrarCliente(MicroservicioCrudBasicoRequestDTO microservicioCrudBasicoRequestDTO) {
		MicroservicioCrudBasicoResponseDTO microservicioCrudBasicoResponseDTO = new MicroservicioCrudBasicoResponseDTO();
		ClientesEntity clientesEntity = new ClientesEntity();
		SequenciaEntity sequenciaEntity = new SequenciaEntity();
		sequenciaEntity = sequenciaRepository.findById("sequence-clientes").orElseThrow();

		secuencia= sequenciaEntity.getValor()+1;

		clientesEntity.setNombre(microservicioCrudBasicoRequestDTO.getNombre());
		clientesEntity.setApellidoPaterno(microservicioCrudBasicoRequestDTO.getApellidoPaterno());
		clientesEntity.setApellidoMaterno(microservicioCrudBasicoRequestDTO.getApellidoMaterno());
		clientesEntity.setCorreo(microservicioCrudBasicoRequestDTO.getCorreo());
		clientesEntity.setEdad(microservicioCrudBasicoRequestDTO.getEdad());
		clientesEntity.setId(secuencia.toString());
		sequenciaEntity.setValor(secuencia);
		sequenciaRepository.save(sequenciaEntity);
		clientesRepository.save(clientesEntity);


		microservicioCrudBasicoResponseDTO.setNombre(clientesEntity.getNombre());
		microservicioCrudBasicoResponseDTO.setApellidoPaterno(clientesEntity.getApellidoPaterno());
		microservicioCrudBasicoResponseDTO.setApellidoMaterno(clientesEntity.getApellidoMaterno());
		microservicioCrudBasicoResponseDTO.setCorreo(clientesEntity.getCorreo());
		microservicioCrudBasicoResponseDTO.setEdad(clientesEntity.getEdad());
		microservicioCrudBasicoResponseDTO.setId(Long.parseLong(clientesEntity.getId()));



		return microservicioCrudBasicoResponseDTO;

	}

	public MicroservicioCrudBasicoResponseDTO consultarCliente(Long id){
		MicroservicioCrudBasicoResponseDTO microservicioCrudBasicoResponseDTO = new MicroservicioCrudBasicoResponseDTO();
		ClientesEntity clientesEntity = new ClientesEntity();
		Optional<ClientesEntity> clientesEntityOptional = clientesRepository.findById(id.toString());
		if (clientesEntityOptional.isPresent()) {

			clientesEntity = clientesEntityOptional.get();

			microservicioCrudBasicoResponseDTO.setNombre(clientesEntity.getNombre());
			microservicioCrudBasicoResponseDTO.setApellidoPaterno(clientesEntity.getApellidoPaterno());
			microservicioCrudBasicoResponseDTO.setApellidoMaterno(clientesEntity.getApellidoMaterno());
			microservicioCrudBasicoResponseDTO.setCorreo(clientesEntity.getCorreo());
			microservicioCrudBasicoResponseDTO.setEdad(clientesEntity.getEdad());
			microservicioCrudBasicoResponseDTO.setId(Long.parseLong(clientesEntity.getId()));

		}else {
			microservicioCrudBasicoResponseDTO= null;
		}

		return microservicioCrudBasicoResponseDTO;
	}


	public List<MicroservicioCrudBasicoResponseDTO> consultarClientes(){
		List<MicroservicioCrudBasicoResponseDTO> microservicioCrudBasicoResponseDTOLista = new ArrayList<MicroservicioCrudBasicoResponseDTO>();
		MicroservicioCrudBasicoResponseDTO microservicioCrudBasicoResponseDTO = null;
		Iterable<ClientesEntity> clientesEntityOptional = clientesRepository.findAll();

		if (clientesEntityOptional!=null) {

			for(ClientesEntity clientesEntity: clientesEntityOptional) {
				microservicioCrudBasicoResponseDTO = new MicroservicioCrudBasicoResponseDTO();
				
				if (clientesEntity.getId().equals("sequence-clientes")) {
					continue;
				}

				microservicioCrudBasicoResponseDTO.setNombre(clientesEntity.getNombre());
				microservicioCrudBasicoResponseDTO.setApellidoPaterno(clientesEntity.getApellidoPaterno());
				microservicioCrudBasicoResponseDTO.setApellidoMaterno(clientesEntity.getApellidoMaterno());
				microservicioCrudBasicoResponseDTO.setCorreo(clientesEntity.getCorreo());
				microservicioCrudBasicoResponseDTO.setEdad(clientesEntity.getEdad());
				microservicioCrudBasicoResponseDTO.setId(Long.parseLong(clientesEntity.getId()));

				microservicioCrudBasicoResponseDTOLista.add(microservicioCrudBasicoResponseDTO);

			}


		}

		return microservicioCrudBasicoResponseDTOLista;
	}


	public boolean eliminarCliente(Long id) {
		boolean eliminado= false;

		Optional<ClientesEntity> clientesEntityOptional = clientesRepository.findById(id.toString());
		ClientesEntity clientesEntity = new ClientesEntity();

		if (clientesEntityOptional.isPresent()) {
			clientesEntity= clientesEntityOptional.get();

			clientesRepository.delete(clientesEntity);
			eliminado=true;
		}
		return eliminado;

	}
	public MicroservicioCrudBasicoResponseDTO actualizarCliente(Long id, MicroservicioCrudBasicoRequestDTO microservicioCrudBasicoRequestDTO) {
		MicroservicioCrudBasicoResponseDTO microservicioCrudBasicoResponseDTO = new MicroservicioCrudBasicoResponseDTO();
		Optional<ClientesEntity> clientesEntityOptional = clientesRepository.findById(id.toString());
		ClientesEntity clientesEntity = new ClientesEntity();

		if (clientesEntityOptional.isPresent()) {

			clientesEntity = clientesEntityOptional.get();

			clientesEntity.setNombre(microservicioCrudBasicoRequestDTO.getNombre());
			clientesEntity.setApellidoPaterno(microservicioCrudBasicoRequestDTO.getApellidoPaterno());
			clientesEntity.setApellidoMaterno(microservicioCrudBasicoRequestDTO.getApellidoMaterno());
			clientesEntity.setCorreo(microservicioCrudBasicoRequestDTO.getCorreo());
			clientesEntity.setEdad(microservicioCrudBasicoRequestDTO.getEdad());

			clientesRepository.save(clientesEntity);


			microservicioCrudBasicoResponseDTO.setNombre(clientesEntity.getNombre());
			microservicioCrudBasicoResponseDTO.setApellidoPaterno(clientesEntity.getApellidoPaterno());
			microservicioCrudBasicoResponseDTO.setApellidoMaterno(clientesEntity.getApellidoMaterno());
			microservicioCrudBasicoResponseDTO.setCorreo(clientesEntity.getCorreo());
			microservicioCrudBasicoResponseDTO.setEdad(clientesEntity.getEdad());
			microservicioCrudBasicoResponseDTO.setId(Long.parseLong(clientesEntity.getId()));
		}






	return microservicioCrudBasicoResponseDTO;

}


}
