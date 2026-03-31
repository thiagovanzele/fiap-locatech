package br.com.fiap.locatech.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Pessoa {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
}
