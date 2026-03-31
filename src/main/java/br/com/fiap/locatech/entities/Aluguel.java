package br.com.fiap.locatech.entities;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Aluguel {

    private Long id;
    private Long pessoaId;
    private Long veiculoId;
    private String veiculoModelo;
    private String pessoaCpf;
    private String nomePessoa;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal valorTotal;
}
