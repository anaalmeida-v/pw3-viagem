package br.com.etechoracio.viagem.repository;

import br.com.etechoracio.viagem.entity.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

}
