package io.github.vinicius9821.service;

import io.github.vinicius9821.domain.entity.Pedido;
import io.github.vinicius9821.domain.entity.StatusPedido;
import io.github.vinicius9821.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar( PedidoDTO dto );
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}