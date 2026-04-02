package br.com.fiap.locatech.repositories.aluguel;

import br.com.fiap.locatech.entities.Aluguel;

import java.util.List;
import java.util.Optional;

public interface AluguelRepository {

    List<Aluguel> findAll(int size, int offset);
    public Optional<Aluguel> findById(Long id);
    public Integer save(Aluguel aluguel);
    public Integer delete(Long id);
    public Integer update(Aluguel aluguel, Long id);
}
