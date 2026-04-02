package br.com.fiap.locatech.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardError {

    Instant  timestamp = Instant.now();
    String message;
    Integer status;
    String path;

}
