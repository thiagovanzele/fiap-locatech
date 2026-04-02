package br.com.fiap.locatech.repositories.pessoa;

import br.com.fiap.locatech.entities.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository {

    List<Pessoa> findAll(int size, int offset);
    public Optional<Pessoa> findById(Long id);
    public Integer save(Pessoa pessoa);
    public Integer delete(Long id);
    public Integer update(Pessoa pessoa, Long id);
}
