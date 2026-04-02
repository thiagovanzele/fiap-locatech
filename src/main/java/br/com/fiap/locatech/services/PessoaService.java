package br.com.fiap.locatech.services;

import br.com.fiap.locatech.entities.Pessoa;
import br.com.fiap.locatech.repositories.pessoa.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return pessoaRepository.findAll(size, offset);
    }

    public Pessoa findById(Long id) {
        return this.pessoaRepository.findById(id)
                .orElse(null);
    }

    public void save(Pessoa pessoa) {
        int save = this.pessoaRepository.save(pessoa);
        Assert.state(save == 1, "Erro ao salvar a pessoa " + pessoa);
    }

    public void update(Pessoa pessoa, Long id) {
        int update = this.pessoaRepository.update(pessoa, id);
        if (update == 0) {
            throw new RuntimeException("Erro ao atualizar a pessoa " + pessoa);
        }
    }

    public void delete(Long id) {
        int delete = this.pessoaRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Erro ao deletar a pessoa " + id);
        }
    }
}
