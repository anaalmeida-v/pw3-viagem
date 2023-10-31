package br.com.etechoracio.viagem.controller;

import br.com.etechoracio.viagem.entity.Gasto;
import br.com.etechoracio.viagem.entity.Viagem;
import br.com.etechoracio.viagem.repository.GastoRepository;
import br.com.etechoracio.viagem.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/viagens")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ViagemController {

	@Autowired
	private ViagemRepository repository;

	@Autowired
	private GastoRepository gastoRepository;

	@GetMapping
	public List<Viagem> listarTodos() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Viagem> buscarPorId(@PathVariable Long id) {
		Optional<Viagem> existe = repository.findById(id);
		return existe.isPresent() ? ResponseEntity.ok(existe.get())
								  : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Viagem> inserir(@RequestBody Viagem obj) {
		return ResponseEntity.ok(repository.save(obj));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Viagem> atualizar(@PathVariable Long id, 
									        @RequestBody Viagem viagem) {
		boolean existe = repository.existsById(id);
		if (!existe) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(repository.save(viagem));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable Long id) {
		boolean existe = repository.existsById(id);
		if (!existe) {
			return ResponseEntity.notFound().build();
		}
		List<Gasto> gastos = gastoRepository.findByViagemId(id);
		if (!gastos.isEmpty()) {
			gastoRepository.deleteAll(gastos);
		}
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}/gastos")
	public List<Gasto> buscarGastosPorId(@PathVariable Long id) {
		return gastoRepository.findByViagemId(id);
	}

}
