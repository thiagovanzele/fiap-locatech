CREATE TABLE veiculo(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    placa VARCHAR(255),
    cor VARCHAR(255),
    ano INT,
    valor_diaria DECIMAL(10, 2)
);

CREATE TABLE pessoa(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(255),
    telefone VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE aluguel(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT NOT NULL,
    veiculo_id BIGINT NOT NULL,
    data_inicio DATE,
    data_fim DATE,
    valor_total DECIMAL(10, 2),

    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),
    FOREIGN KEY (veiculo_id) REFERENCES veiculo(id)
);


INSERT INTO veiculo (marca, modelo, placa, cor, ano, valor_diaria) VALUES
               ('Chevrolet', 'Celta', 'ABC-1234', 'Vermelho', 2011, 100.00);

INSERT INTO pessoa (nome, cpf, telefone, email) VALUES
    ('Thiago', '123456789', '11912345678', 'thiago@fiap.com');

INSERT INTO aluguel (pessoa_id, veiculo_id, data_inicio, data_fim, valor_total) VALUES
    ('1', '1', '2026-01-01', '2026-01-05', 500.00);
