package mx.com.microservicio.basico.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.microservicio.basico.dto.MicroservicioCrudBasicoRequestDTO;
import mx.com.microservicio.basico.dto.MicroservicioCrudBasicoResponseDTO;

import mx.com.microservicio.basico.service.MicroservicioCrudBasicoService;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/badak")
public class MicroservicioCrudBasicoController {
	
	
	@Autowired
	public MicroservicioCrudBasicoService microservicioCrudBasicoService;
	
	
	@PostMapping("/registrar/cliente")
	public ResponseEntity<MicroservicioCrudBasicoResponseDTO> registrarCliente(@RequestBody MicroservicioCrudBasicoRequestDTO microservicioCrudBasicoRequestDTO) {
		MicroservicioCrudBasicoResponseDTO microservicioCrudBasicoResponseDTO = microservicioCrudBasicoService.registrarCliente(microservicioCrudBasicoRequestDTO);
		
		return ResponseEntity.ok(microservicioCrudBasicoResponseDTO);
	}
	
	
	@GetMapping("/consultar/cliente/{id}")
	public ResponseEntity<MicroservicioCrudBasicoResponseDTO> consultarCliente(@PathVariable Long id) {
		
		MicroservicioCrudBasicoResponseDTO microservicioCrudBasicoResponseDTO = microservicioCrudBasicoService.consultarCliente(id);
		
		if (microservicioCrudBasicoResponseDTO== null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(microservicioCrudBasicoResponseDTO);
	}
	
	@GetMapping("/consultar/clientes")
	public ResponseEntity<List<MicroservicioCrudBasicoResponseDTO>> consulatarClientes() {
		List<MicroservicioCrudBasicoResponseDTO> listmicroservicioCrudBasicoResponseDTOlista = new ArrayList<MicroservicioCrudBasicoResponseDTO>();
		
		listmicroservicioCrudBasicoResponseDTOlista = microservicioCrudBasicoService.consultarClientes();
		
		if (listmicroservicioCrudBasicoResponseDTOlista==null) {
			return ResponseEntity.notFound().build();
		}
		
		
		return ResponseEntity.ok(listmicroservicioCrudBasicoResponseDTOlista);
	}
	
	@DeleteMapping("/eliminar/cliente/{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
	
		Boolean eliminado = microservicioCrudBasicoService.eliminarCliente(id);
		
		if (!eliminado) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/actualizar/cliente/{id}")
	public ResponseEntity<MicroservicioCrudBasicoResponseDTO> actualizarCliente	(@PathVariable Long id, @RequestBody MicroservicioCrudBasicoRequestDTO microservicioCrudBasicoRequestDTO) {
		
		
		
		MicroservicioCrudBasicoResponseDTO microservicioCrudBasicoResponseDTO = microservicioCrudBasicoService.actualizarCliente(id,microservicioCrudBasicoRequestDTO);
		
		if (microservicioCrudBasicoResponseDTO==null) {
			return ResponseEntity.notFound().build();
		}
		
		
		return ResponseEntity.ok(microservicioCrudBasicoResponseDTO);
	}
	
	
	

}
