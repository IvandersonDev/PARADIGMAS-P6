package br.com.balanca.exceptions;

public class TipoBalancaNaoSuportadoException extends Exception {
    public TipoBalancaNaoSuportadoException(String message) {
        super(message);
    }
}
