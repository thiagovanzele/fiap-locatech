package br.com.fiap.locatech.controllers;

import br.com.fiap.locatech.entities.Veiculo;
import br.com.fiap.locatech.services.VeiculoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> findAll(@RequestParam(required = false) int page,
                                                @RequestParam(required = false) int size) {
        log.debug("Foi acessado o endpoint de veículos /veiculos");
        List<Veiculo> veiculos = veiculoService.findAll(page, size);
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> findById(@PathVariable Long veiculoId) {
        log.debug("Buscando veiculo por veiculoId {}", veiculoId);
        Veiculo veiculo = this.veiculoService.findById(veiculoId);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody Veiculo veiculo){
        log.debug("Salvando veiculo {}", veiculo);
        this.veiculoService.save(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/atualizar/{veiculoId}")
    public ResponseEntity<Void> update(@RequestBody Veiculo veiculo, @PathVariable Long veiculoId){
        log.debug("Atualizando veiculo {}", veiculoId);
        this.veiculoService.update(veiculo, veiculoId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/excluir/{veiculoId}")
    public ResponseEntity<Void> delete(@PathVariable Long veiculoId){
        log.debug("Deletando veiculo {}", veiculoId);
        this.veiculoService.delete(veiculoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
