CREATE TABLE veiculo(
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      marca VARCHAR(255),
      modelo VARCHAR(255),
      placa VARCHAR(255),
      cor VARCHAR(255),
      ano INT,
      valor_diaria DECIMAL(10, 2)
);

INSERT INTO veiculo (marca, modelo, placa, cor, ano, valor_diaria) VALUES
               ('Chevrolet', 'Celta', 'ABC-1234', 'Vermelho', 2011, 100.00);