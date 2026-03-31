package br.com.fiap.locatech.services;

import br.com.fiap.locatech.entities.Veiculo;
import br.com.fiap.locatech.repositories.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo findById(Long id){
        return this.veiculoRepository.findById(id).orElse(null);
    }

    public List<Veiculo> findAll(int page, int size) {
        int offset = (page -1) * size;

        return this.veiculoRepository.findAll(size, offset);
    }

    public void save(Veiculo veiculo){
        var save = this.veiculoRepository.save(veiculo);
        Assert.state(save == 1, "Erro ao salvar o veículo " + veiculo.getModelo());
    }

    public void update(Veiculo veiculo, Long id){
        var update = this.veiculoRepository.update(veiculo, id);
        if (update == 0) {
            throw new RuntimeException("Nenhum veiculo encontrado");
        }
    }

    public void delete(Long id){
        var delete = this.veiculoRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Nenhum veiculo encontrado");
        }
    }
}
