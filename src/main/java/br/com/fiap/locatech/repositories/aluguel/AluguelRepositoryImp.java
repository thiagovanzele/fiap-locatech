package br.com.fiap.locatech.repositories.aluguel;

import br.com.fiap.locatech.entities.Aluguel;
import br.com.fiap.locatech.entities.Pessoa;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AluguelRepositoryImp implements AluguelRepository {

    private final JdbcClient jdbc;
    public AluguelRepositoryImp(JdbcClient jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<Aluguel> findById(Long id) {
        return jdbc.sql("SELECT a.id, a.pessoa_id, a.veiculo_id, a.data_inicio, a.data_fim, a.valor_total, " +
                        "p.nome AS pessoa_nome, p.cpf AS pessoa_cpf, " +
                        "v.modelo AS veiculo_modelo, v.placa AS veiculo_placa " +
                        "FROM aluguel a " +
                        "INNER JOIN pessoa p on a.pessoa_id = p.id " +
                        "INNER JOIN veiculo v on a.veiculo_id = v.id " +
                        "WHERE a.id = :id")
                .param("id", id)
                .query(Aluguel.class)
                .optional();
    }

    @Override
    public List<Aluguel> findAll(int size, int offset) {
        return this.jdbc.sql("SELECT a.id, a.pessoa_id, a.veiculo_id, a.data_inicio, a.data_fim, a.valor_total, " +
                        "p.nome AS pessoa_nome, p.cpf AS pessoa_cpf, " +
                        "v.modelo AS veiculo_modelo, v.placa AS veiculo_placa " +
                        "FROM aluguel a " +
                        "INNER JOIN pessoa p on a.pessoa_id = p.id " +
                        "INNER JOIN veiculo v on a.veiculo_id = v.id " +
                        "LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Aluguel.class)
                .list();
    }

    @Override
    public Integer save(Aluguel aluguel) {
        return this.jdbc.sql("INSERT INTO aluguel (pessoa_id, veiculo_id, data_inicio, data_fim, valor_total) " +
                        "VALUES (:pessoaId, :veiculoId, :data_inicio, :data_fim, :valor_total)")
                .param("pessoaId", aluguel.getPessoaId())
                .param("veiculoId", aluguel.getVeiculoId())
                .param("data_inicio", aluguel.getDataInicio())
                .param("data_fim", aluguel.getDataFim())
                .param("valor_total", aluguel.getValorTotal())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbc.sql("DELETE FROM aluguel WHERE id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public Integer update(Aluguel aluguel, Long id) {
        return this.jdbc.sql(("UPDATE aluguel SET pessoaId = :pessoaId, " +
                "veiculoId = :veiculoId, data_inicio = :data_inicio, data_fim = :data_fim, valo_total = :valor_total"))
                .param("pessoaId", aluguel.getPessoaId())
                .param("veiculoId", aluguel.getVeiculoId())
                .param("data_inicio", aluguel.getDataInicio())
                .param("data_fim", aluguel.getDataFim())
                .param("valor_total", aluguel.getValorTotal())
                .update();
    }
}
