package com.kadukitesesi.marmitas.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaInicial extends JFrame {

    int contador = 0;

    public PaginaInicial() throws HeadlessException {
        super("Pagina inicial");

        JLabel jLabel = new JLabel();
        jLabel.setBounds(100,200,50,50);

        JButton jButton = new JButton("Botão");
        jButton.setBounds(100,100,100,80);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contador++;
                jLabel.setText(String.valueOf(contador));
            }
        });

        JTextField campo = new JTextField();
        campo.setBounds(200, 200, 150, 30);
        campo.setName("Nome");




        add(jLabel);
        add(jButton);
        add(campo);
        setLayout(null);
    }


    public static void main(String[] args) {
        PaginaInicial paginaInicial = new PaginaInicial();
        paginaInicial.setVisible(true);
        paginaInicial.setSize(400, 200);

    }
}
