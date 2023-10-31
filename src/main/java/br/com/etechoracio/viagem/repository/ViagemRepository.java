package br.com.etechoracio.viagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.etechoracio.viagem.entity.Viagem;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

	
}
