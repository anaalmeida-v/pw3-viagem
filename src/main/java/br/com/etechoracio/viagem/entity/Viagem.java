package br.com.etechoracio.viagem.entity;

import br.com.etechoracio.viagem.enums.TipoViagemEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="TBL_VIAGEM")
public class Viagem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_VIAGEM")
	private Long id;
	
	@Column(name="TX_DESTINO")
	private String destino;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TP_VIAGEM")
	private TipoViagemEnum tipo;
	
	@Column(name="DT_CHEGADA")
	private LocalDate chegada;
	
	@Column(name="DT_SAIDA")
	private LocalDate saida;
	
	@Column(name="VLR_ORCAMENTO")
	private Double orcamento;
	
	@Column(name="QTD_PESSOAS")
	private Integer pessoas;

}
