package com.kadukitesesi.marmitas.view;

import com.kadukitesesi.marmitas.model.ProdutoModel;
import com.kadukitesesi.marmitas.service.ProdutoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoView extends JFrame {

    private final ProdutoService produtoService;

    public ProdutoView(ProdutoService produtoService) {
        this.produtoService = produtoService;

        setTitle("Produtos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        initComponents(panel);

        setVisible(true);
    }

    private void initComponents(JPanel panel) {
        panel.setLayout(null);

        JTextArea produtoListArea = new JTextArea();
        produtoListArea.setBounds(10, 20, 460, 200);
        produtoListArea.setEditable(false);
        panel.add(produtoListArea);

        JButton refreshButton = new JButton("Atualizar Lista");
        refreshButton.setBounds(10, 230, 150, 30);
        panel.add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaProdutos(produtoListArea);
            }
        });

        JButton addButton = new JButton("Adicionar Produto");
        addButton.setBounds(170, 230, 150, 30);
        panel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });

        JButton deleteButton = new JButton("Excluir Produto");
        deleteButton.setBounds(330, 230, 150, 30);
        panel.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirProduto();
            }
        });

        atualizarListaProdutos(produtoListArea);
    }

    private void atualizarListaProdutos(JTextArea produtoListArea) {
        List<ProdutoModel> produtos = produtoService.obterProdutos();
        produtoListArea.setText("");

        for (ProdutoModel produto : produtos) {
            produtoListArea.append(produto.getNome() + " - R$ " + produto.getPreco() + "\n");
        }
    }

    private void adicionarProduto() {
        String nome = JOptionPane.showInputDialog(this, "Nome do Produto:");
        String preco = JOptionPane.showInputDialog(this, "Preço do Produto:");

        if (nome != null && preco != null) {
            ProdutoModel produto = new ProdutoModel(nome, new BigDecimal(preco));
            produtoService.criarProduto(produto);
            JOptionPane.showMessageDialog(this, "Produto Adicionado com Sucesso!");
        }
    }

    private void excluirProduto() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome do Produto para excluir:");

        if (nome != null) {
            produtoService.excluirProduto(nome);
            JOptionPane.showMessageDialog(this, "Produto Excluído com Sucesso!");
        }
    }

    public static void main(String[] args) {
        ProdutoService produtoService = new ProdutoService();
        new ProdutoView(produtoService);
    }
}
