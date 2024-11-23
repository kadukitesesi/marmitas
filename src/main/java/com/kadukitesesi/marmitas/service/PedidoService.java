package com.kadukitesesi.marmitas.service;

import com.kadukitesesi.marmitas.model.PedidoModel;
import com.kadukitesesi.marmitas.model.StatusPedido;
import com.kadukitesesi.marmitas.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    public PedidoModel fazerPedido(PedidoModel pedido) {
        return pedidoRepository.save(pedido);
    }
    
    public List<PedidoModel> listarPedidos() {
        List<PedidoModel> pedidosPorOrdem = pedidoRepository
                .findAll(Sort.by("dataPedido"));
                return pedidosPorOrdem;
    }

    public PedidoModel atualizarStatus(Long id, String novoStatus) {
        PedidoModel pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido não encontrado"));

        try {
            StatusPedido statusPedidoAtual = pedido.getStatusPedido();
            StatusPedido novoStatusPedido = StatusPedido.valueOf(novoStatus.toUpperCase());

            if (novoStatusPedido.getStatusAnterior() != null
                    && !novoStatusPedido.getStatusAnterior().equals(statusPedidoAtual)) {
                throw new IllegalArgumentException("Transição de status inválida: "
                        + statusPedidoAtual + " para " + novoStatusPedido);
            }

            pedido.setStatusPedido(novoStatusPedido);
            return pedidoRepository.save(pedido);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status inválido ou transição não permitida");
        }
    }
}

