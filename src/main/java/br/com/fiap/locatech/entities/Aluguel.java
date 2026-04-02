package br.com.fiap.locatech.entities;

import br.com.fiap.locatech.dtos.request.AluguelRequest;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Aluguel {

    private Long id;
    private Long pessoaId;
    private Long veiculoId;
    private String veiculoModelo;
    private String veiculoPlaca;
    private String pessoaCpf;
    private String pessoaNome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal valorTotal;

    public static Aluguel fromDto(AluguelRequest request, BigDecimal valorTotal) {
        return builder()
                .pessoaId(request.pessoaId())
                .veiculoId(request.veiculoId())
                .dataInicio(request.dataInicio())
                .dataFim(request.dataFim())
                .valorTotal(valorTotal)
                .build();
    }

}
