package com.kadukitesesi.marmitas.service;

import com.kadukitesesi.marmitas.model.ClienteModel;
import com.kadukitesesi.marmitas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public List<ClienteModel> obterClientes() {
        List<ClienteModel> clientes = clienteRepository.findAll();
        return clientes;
    }

    public ClienteModel buscarClientePorNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    public ClienteModel criarCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    public void excluirCliente(String nome) {
        clienteRepository.deleteByNome(nome);
    }


}
