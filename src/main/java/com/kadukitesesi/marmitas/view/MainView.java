package com.kadukitesesi.marmitas.view;

import com.kadukitesesi.marmitas.service.ClienteService;
import com.kadukitesesi.marmitas.service.PedidoService;
import com.kadukitesesi.marmitas.service.ProdutoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final PedidoService pedidoService;

    public MainView(ClienteService clienteService, ProdutoService produtoService, PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.pedidoService = pedidoService;

        setTitle("Tela Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        initComponents(panel);

        setVisible(true);
    }

    private void initComponents(JPanel panel) {
        panel.setLayout(null);

        JButton clientesButton = new JButton("Clientes");
        clientesButton.setBounds(150, 50, 100, 30);
        panel.add(clientesButton);
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClienteView(clienteService);
                dispose();
            }
        });


        JButton produtosButton = new JButton("Produtos");
        produtosButton.setBounds(150, 100, 100, 30);
        panel.add(produtosButton);
        produtosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProdutoView(produtoService);
                dispose();
            }
        });

        JButton pedidosButton = new JButton("Pedidos");
        pedidosButton.setBounds(150, 150, 100, 30);
        panel.add(pedidosButton);
        pedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PedidoView(clienteService, produtoService, pedidoService);
                dispose();
            }
        });
    }

    public static void main(String[] args) {

        ClienteService clienteService = new ClienteService();
        ProdutoService produtoService = new ProdutoService();
        PedidoService pedidoService = new PedidoService();

         new MainView(clienteService, produtoService, pedidoService);
    }
}
