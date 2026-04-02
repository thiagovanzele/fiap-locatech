package br.com.fiap.locatech.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardValidationError {

    Instant  timestamp = Instant.now();
    List<String> messages;
    Integer status;
    String path;

}
