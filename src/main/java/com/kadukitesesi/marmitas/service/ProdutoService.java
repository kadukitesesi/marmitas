package com.kadukitesesi.marmitas.service;

import com.kadukitesesi.marmitas.model.ProdutoModel;
import com.kadukitesesi.marmitas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoModel criarProduto(ProdutoModel produtoModel) {
        return produtoRepository.save(produtoModel);
    }

    public void excluirProduto(String nome) {
        produtoRepository.deleteByNome(nome);
    }

    public List<ProdutoModel> obterProdutos() {
        return produtoRepository.findAll();
    }
}
