package io.github.vinicius9821.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException() {
        super("Pedido nao encontrado");
    }
}
