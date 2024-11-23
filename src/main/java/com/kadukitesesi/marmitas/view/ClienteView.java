package com.kadukitesesi.marmitas.view;

import com.kadukitesesi.marmitas.model.ClienteModel;
import com.kadukitesesi.marmitas.service.ClienteService;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClienteView extends JFrame {

    private final ClienteService clienteService;

    public ClienteView(ClienteService clienteService) {
        this.clienteService = clienteService;

        setTitle("Clientes");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        initComponents(panel);

        setVisible(true);
    }

    private void initComponents(JPanel panel) {
        panel.setLayout(null);

        JTextArea clienteListArea = new JTextArea();
        clienteListArea.setBounds(10, 20, 460, 200);
        clienteListArea.setEditable(false);
        panel.add(clienteListArea);

        JButton refreshButton = new JButton("Atualizar Lista");
        refreshButton.setBounds(10, 230, 150, 30);
        panel.add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaClientes(clienteListArea);
            }
        });

        JButton addButton = new JButton("Adicionar Cliente");
        addButton.setBounds(170, 230, 150, 30);
        panel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCliente();
            }
        });

        JButton deleteButton = new JButton("Excluir Cliente");
        deleteButton.setBounds(330, 230, 150, 30);
        panel.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCliente();
            }
        });

        // Inicializa a lista de clientes
        atualizarListaClientes(clienteListArea);
    }

    private void atualizarListaClientes(JTextArea clienteListArea) {
        List<ClienteModel> clientes = clienteService.obterClientes();
        clienteListArea.setText("");

        for (ClienteModel cliente : clientes) {
            clienteListArea.append(cliente.getNome() + " - " + cliente.getTelefone() + "\n");
        }
    }

    private void adicionarCliente() throws ParseException {
        String nome = JOptionPane.showInputDialog(this, "Nome do Cliente:");
        String telefone = JOptionPane.showInputDialog(this, "Telefone do Cliente:");
        String endereco = JOptionPane.showInputDialog(this, "endereco do Cliente:");
        String aniversarioStr = JOptionPane.showInputDialog(this, "aniversario do Cliente:");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date aniversario = dateFormat.parse(aniversarioStr);

        if (!nome.isEmpty() && !telefone.isEmpty() && !endereco.isEmpty() && aniversario != null) {
            ClienteModel cliente = new ClienteModel(nome, telefone, endereco, aniversario);
            clienteService.criarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente Adicionado com Sucesso!");
        }
    }

    private void excluirCliente() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome do Cliente para excluir:");

        if (nome != null) {
            clienteService.excluirCliente(nome);
            JOptionPane.showMessageDialog(this, "Cliente Excluído com Sucesso!");
        }
    }

    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();
        new ClienteView(clienteService);
    }
}
