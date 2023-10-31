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
@RequestMapping("/gastos")
public class GastoController {

	@Autowired
	private GastoRepository repository;

	@Autowired
	private ViagemRepository viagemRepository;

	@GetMapping
	public List<Gasto> listarTodos() {
		return repository.findAll();
	}


	@GetMapping("/{id}")
	public ResponseEntity<Gasto> buscarPorId(@PathVariable Long id) {
		Optional<Gasto> existe = repository.findById(id);
		return existe.isPresent() ? ResponseEntity.ok(existe.get())
								  : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable Long id) {
		boolean existe = repository.existsById(id);
		if (existe) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}


	@PostMapping
	public ResponseEntity<Gasto> inserir(@RequestBody Gasto obj) {
		Optional<Viagem> existe = viagemRepository.findById(obj.getViagem().getId());
		if (!existe.isPresent()) {
			throw new RuntimeException("Viagem não encontrada.");
		}

		Viagem viagem = existe.get();
		if (viagem.getSaida() != null) {
			throw new RuntimeException("Viagem já finalizada");
		}

		if (obj.getData().isBefore(viagem.getChegada())) {
			throw new RuntimeException("Data do gasto anterior a data da viagem");
		}

		return ResponseEntity.ok(repository.save(obj));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Gasto> atualizar(@PathVariable Long id, 
									       @RequestBody Gasto gasto) {
		boolean existe = repository.existsById(id);
		if (!existe) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(repository.save(gasto));
	}

}
