package br.com.fiap.locatech.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException {

    public ObjetoNaoEncontradoException() {
        super("Objeto não encontrado");
    }

    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }
}
