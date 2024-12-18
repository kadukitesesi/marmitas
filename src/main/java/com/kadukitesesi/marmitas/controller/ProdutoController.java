package com.kadukitesesi.marmitas.controller;


import com.kadukitesesi.marmitas.model.ProdutoModel;
import com.kadukitesesi.marmitas.service.ProdutoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoModel>> obterProdutos() {
        List<ProdutoModel> produtos = produtoService.obterProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoModel> obterProdutoId(Long id) {
        ProdutoModel produto = produtoService.obterProduto(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping("/produtos")
    public ResponseEntity<ProdutoModel> criarProduto(@RequestBody  ProdutoModel produto) {
        ProdutoModel produtoModel = produtoService.criarProduto(produto);
        return ResponseEntity.status(201).body(produtoModel);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable String nome) {
        produtoService.excluirProduto(nome);
        return ResponseEntity.noContent().build();
    }
}
