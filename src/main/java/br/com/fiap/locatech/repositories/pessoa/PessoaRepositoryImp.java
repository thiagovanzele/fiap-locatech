package br.com.fiap.locatech.repositories.pessoa;

import br.com.fiap.locatech.entities.Pessoa;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PessoaRepositoryImp implements PessoaRepository {

    private final JdbcClient jdbc;
    public PessoaRepositoryImp(JdbcClient jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Pessoa> findAll(int size, int offset) {
        return jdbc.sql("SELECT * FROM pessoa LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Pessoa.class)
                .list();
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return this.jdbc.sql("SELECT * FROM pessoa WHERE id = :id")
                .param("id", id)
                .query(Pessoa.class)
                .optional();
    }

    @Override
    public Integer save(Pessoa pessoa) {
        return this.jdbc.sql("INSERT INTO pessoa (nome, cpf, telefone, email) " +
                "VALUES (:nome, :cpf, :telefone, :email)")
                .param("nome", pessoa.getNome())
                .param("cpf", pessoa.getCpf())
                .param("telefone", pessoa.getTelefone())
                .param("email", pessoa.getEmail())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbc.sql("DELETE FROM pessoa WHERE id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public Integer update(Pessoa pessoa, Long id) {
        return this.jdbc.sql("UPDATE pessoa SET nome = :nome, cpf = :cpf, telefone = :telefone, email = :email " +
                "WHERE id = :id")
                .param("nome", pessoa.getNome())
                .param("cpf", pessoa.getCpf())
                .param("telefone", pessoa.getTelefone())
                .param("email", pessoa.getEmail())
                .param("id", id)
                .update();
    }
}
