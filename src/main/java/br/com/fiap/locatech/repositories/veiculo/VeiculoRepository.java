package br.com.fiap.locatech.repositories.veiculo;

import br.com.fiap.locatech.entities.Veiculo;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository {

    Optional<Veiculo> findById(Long id);
    List<Veiculo> findAll(int size, int offset);
    Integer save(Veiculo veiculo);
    Integer update(Veiculo veiculo, Long id);
    Integer delete(Long id);
}
