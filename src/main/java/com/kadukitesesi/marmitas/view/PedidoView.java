package com.kadukitesesi.marmitas.view;

import com.kadukitesesi.marmitas.model.*;
import com.kadukitesesi.marmitas.service.ClienteService;
import com.kadukitesesi.marmitas.service.PedidoService;
import com.kadukitesesi.marmitas.service.ProdutoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

public class PedidoView extends JFrame {

    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final PedidoService pedidoService;

    public PedidoView(ClienteService clienteService, ProdutoService produtoService, PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.pedidoService = pedidoService;

        setTitle("Criar Pedido");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        initComponents(panel);

        setVisible(true);
    }

    private void initComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel clienteLabel = new JLabel("Cliente:");
        clienteLabel.setBounds(10, 20, 80, 25);
        panel.add(clienteLabel);

        JTextField clienteField = new JTextField(20);
        clienteField.setBounds(100, 20, 165, 25);
        panel.add(clienteField);


        JLabel produtoLabel = new JLabel("Produto:");
        produtoLabel.setBounds(10, 50, 80, 25);
        panel.add(produtoLabel);

        JTextField produtoField = new JTextField(20);
        produtoField.setBounds(100, 50, 165, 25);
        panel.add(produtoField);

        JLabel quantidadeLabel = new JLabel("Quantidade:");
        quantidadeLabel.setBounds(10, 80, 80, 25);
        panel.add(quantidadeLabel);

        JTextField quantidadeField = new JTextField(20);
        quantidadeField.setBounds(100, 80, 165, 25);
        panel.add(quantidadeField);

        JLabel pagamentoLabel = new JLabel("Pagamento:");
        pagamentoLabel.setBounds(10, 110, 100, 25);
        panel.add(pagamentoLabel);

        JComboBox<FormaDePagamento> pagamentoCombo = new JComboBox<>(FormaDePagamento.values());
        pagamentoCombo.setBounds(100, 110, 165, 25);
        panel.add(pagamentoCombo);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(10, 140, 100, 25);
        panel.add(statusLabel);

        JComboBox<StatusPedido> statusCombo = new JComboBox<>(StatusPedido.values());
        statusCombo.setBounds(100, 140, 165, 25);
        panel.add(statusCombo);

        JLabel precoTotalLabel = new JLabel("Preço Total:");
        precoTotalLabel.setBounds(10, 170, 100, 25);
        panel.add(precoTotalLabel);

        JTextField precoTotalField = new JTextField();
        precoTotalField.setBounds(100, 170, 165, 25);
        precoTotalField.setEditable(false);
        panel.add(precoTotalField);

        JButton criarButton = new JButton("Criar Pedido");
        criarButton.setBounds(10, 200, 150, 25);
        panel.add(criarButton);

        criarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClienteModel cliente = clienteService.buscarClientePorNome(clienteField.getText());
                    if (cliente == null) {
                        JOptionPane.showMessageDialog(panel, "Cliente não encontrado!");
                        return;
                    }

                    List<ProdutoModel> produtosSelecionados = produtoService.obterProdutos();
                    if (produtosSelecionados.isEmpty()) {
                        JOptionPane.showMessageDialog(panel, "Nenhum produto encontrado!");
                        return;
                    }

                    PedidoModel pedido = new PedidoModel();
                    pedido.setCliente(cliente);
                    pedido.setProdutos(produtosSelecionados);
                    pedido.setQuantidade(Integer.parseInt(quantidadeField.getText()));
                    pedido.setFormaDePagamento((FormaDePagamento) pagamentoCombo.getSelectedItem());
                    pedido.setStatusPedido((StatusPedido) statusCombo.getSelectedItem());

                    BigDecimal precoTotal = pedido.calcularPrecoTotal();
                    precoTotalField.setText(precoTotal.toString());

                    pedidoService.fazerPedido(pedido);
                    JOptionPane.showMessageDialog(panel, "Pedido criado com sucesso!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Erro ao criar pedido: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();
        ProdutoService produtoService = new ProdutoService();
        PedidoService pedidoService = new PedidoService();

        new PedidoView(clienteService, produtoService, pedidoService);
    }
}
