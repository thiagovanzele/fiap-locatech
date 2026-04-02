package br.com.fiap.locatech.controllers;

import br.com.fiap.locatech.entities.Pessoa;
import br.com.fiap.locatech.entities.Veiculo;
import br.com.fiap.locatech.services.PessoaService;
import br.com.fiap.locatech.services.VeiculoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        log.info("Foi acessado o endpoint de pessoas /pessoas");
        List<Pessoa> pessoas = pessoaService.findAll(page, size);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long pessoaId) {
        log.info("Buscando pessoa por pessoaId {}", pessoaId);
        Pessoa pessoa = this.pessoaService.findById(pessoaId);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody Pessoa pessoa){
        log.info("Salvando pessoa {}", pessoa);
        this.pessoaService.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/atualizar/{pessoaId}")
    public ResponseEntity<Void> update(@RequestBody Pessoa pessoa, @PathVariable Long pessoaId){
        log.info("Atualizando pessoa {}", pessoaId);
        this.pessoaService.update(pessoa, pessoaId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/excluir/{pessoaId}")
    public ResponseEntity<Void> delete(@PathVariable Long pessoaId){
        log.info("Deletando pessoa {}", pessoaId);
        this.pessoaService.delete(pessoaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
