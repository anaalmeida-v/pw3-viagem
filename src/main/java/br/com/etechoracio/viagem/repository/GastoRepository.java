package br.com.etechoracio.viagem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.etechoracio.viagem.entity.Gasto;

public interface GastoRepository extends JpaRepository<Gasto, Long> {
	
	List<Gasto> findByViagemId(Long id);
	
}
