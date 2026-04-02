package br.com.fiap.locatech.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequest(
        @NotNull(message = "Id de pessoa é obrigatório")
        @Schema(description = "Id da pessoa que está alugando")
        Long pessoaId,

        @NotNull(message = "Id do veículo é obrigatório")
        Long veiculoId,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
