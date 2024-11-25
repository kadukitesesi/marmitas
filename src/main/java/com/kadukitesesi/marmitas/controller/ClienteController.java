package com.kadukitesesi.marmitas.controller;

import com.kadukitesesi.marmitas.model.ClienteModel;
import com.kadukitesesi.marmitas.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/obter")
    public ResponseEntity<List<ClienteModel>> listarClientes() {
        List<ClienteModel> clientes = clienteService.obterClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/obter/{nome}")
    public ResponseEntity<ClienteModel> listarClientePorNome(@PathVariable String nome) {
        ClienteModel cliente = clienteService.buscarClientePorNome(nome);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/criar")
    public ResponseEntity<ClienteModel> criarCliente(@RequestBody ClienteModel clienteModel) {
        ClienteModel cliente = clienteService.criarCliente(clienteModel);
        return ResponseEntity.status(201).body(cliente);
    }

    @DeleteMapping("/deletar/{nome}")
    public ResponseEntity<Void> excluirCliente(@PathVariable String nome) {
        clienteService.excluirCliente(nome);
        return ResponseEntity.status(204).body(null);
    }
}
