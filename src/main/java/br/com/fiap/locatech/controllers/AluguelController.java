package br.com.fiap.locatech.controllers;

import br.com.fiap.locatech.dtos.request.AluguelRequest;
import br.com.fiap.locatech.entities.Aluguel;
import br.com.fiap.locatech.entities.Pessoa;
import br.com.fiap.locatech.services.AluguelService;
import br.com.fiap.locatech.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/alugueis")
@Tag(name = "Aluguel", description = "Controller para CRUD de alugueis")
public class AluguelController {

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @GetMapping
    @Operation(
            description = "Busca todos os alugueis paginados",
            summary = "Busca de alugueis",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    public ResponseEntity<List<Aluguel>> findAll(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        log.info("Foi acessado o endpoint de alugueis /aluguel");
        List<Aluguel> alugueis = aluguelService.findAll(page, size);
        return ResponseEntity.ok(alugueis);
    }

    @GetMapping("/{aluguelId}")
    public ResponseEntity<Aluguel> findById(@PathVariable Long aluguelId) {
        log.info("Buscando aluguel por id {}", aluguelId);
        Aluguel aluguel = this.aluguelService.findById(aluguelId);
        return ResponseEntity.ok(aluguel);
    }

    @PostMapping()
    public ResponseEntity<Void> save(@Valid @RequestBody AluguelRequest request){
        log.info("Salvando aluguel {}", request);
        this.aluguelService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/atualizar/{aluguelId}")
    public ResponseEntity<Void> update(@RequestBody Aluguel aluguel, @PathVariable Long aluguelId){
        log.info("Atualizando pessoa {}", aluguelId);
        this.aluguelService.update(aluguel, aluguelId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/excluir/{aluguelId}")
    public ResponseEntity<Void> delete(@PathVariable Long aluguelId){
        log.info("Deletando aluguel {}", aluguelId);
        this.aluguelService.delete(aluguelId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
