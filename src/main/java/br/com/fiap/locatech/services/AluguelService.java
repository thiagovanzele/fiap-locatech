package br.com.fiap.locatech.services;

import br.com.fiap.locatech.dtos.request.AluguelRequest;
import br.com.fiap.locatech.entities.Aluguel;
import br.com.fiap.locatech.entities.Pessoa;
import br.com.fiap.locatech.entities.Veiculo;
import br.com.fiap.locatech.exceptions.ObjetoNaoEncontradoException;
import br.com.fiap.locatech.repositories.aluguel.AluguelRepository;
import br.com.fiap.locatech.repositories.pessoa.PessoaRepository;
import br.com.fiap.locatech.repositories.veiculo.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final VeiculoRepository veiculoRepository;
    private final PessoaRepository pessoaRepository;

    public AluguelService(AluguelRepository aluguelRepository, VeiculoRepository veiculoRepository, PessoaRepository pessoaRepository ) {
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public List<Aluguel> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return aluguelRepository.findAll(size, offset);
    }

    public Aluguel findById(Long id) {
        return this.aluguelRepository.findById(id)
                .orElse(null);
    }

    public void save(AluguelRequest request) {
        Aluguel aluguel = calculaAluguel(request);
        int save = this.aluguelRepository.save(aluguel);
        Assert.state(save == 1, "Erro ao salvar aluguel " + request);
    }

    public void update(Aluguel aluguel, Long id) {
        int update = this.aluguelRepository.update(aluguel, id);
        if (update == 0) {
            throw new RuntimeException("Erro ao atualizar aluguel " + aluguel);
        }
    }

    public void delete(Long id) {
        int delete = this.aluguelRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Erro ao deletar aluguel " + id);
        }
    }

    private Aluguel calculaAluguel(AluguelRequest aluguelRequest) {
        Veiculo veiculo = veiculoRepository.findById(aluguelRequest.veiculoId())
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Veiculo não encontrado"));

        long quantidadeDias = ChronoUnit.DAYS.between(aluguelRequest.dataInicio(), aluguelRequest.dataFim());
        BigDecimal valorTotal = veiculo.getValorDiaria().multiply(BigDecimal.valueOf(quantidadeDias));

        return Aluguel.fromDto(aluguelRequest, valorTotal);

    }
}
