package com.example.backend.Service;

import com.example.backend.Domain.Estoque;
import com.example.backend.Exception.GenericHTTPException;
import com.example.backend.Repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import java.util.List;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public EstoqueService(
            EstoqueRepository estoqueRepository
    ){
        this.estoqueRepository = estoqueRepository;
    }

    public List<Estoque> getAll(){
        List<Estoque> produtos = estoqueRepository.findAll();
        // if(produtos == null || produtos.isEmpty()){
        //     throw new GenericHTTPException("Nenhum produto encontrado", Status.NOT_FOUND);
        // }
        return produtos;
    }

    public Estoque save(Estoque produto){
        estoqueRepository.save(produto);
        return produto;
    }

    public Estoque update(Long id, Estoque produto){
        var produtoVelho = estoqueRepository.findById(id);
        if(produtoVelho == null){
            throw new GenericHTTPException("Produto não encontrado", Status.NOT_FOUND);
        }
        produtoVelho.setNome(produto.getNome());
        produtoVelho.setQuantidade(produto.getQuantidade());
        produtoVelho.setValor(produto.getValor());
        return produto;
    }

    public void delete(Long id){
        var produtoDeletado = estoqueRepository.findById(id);
        if(produtoDeletado == null){
            throw new GenericHTTPException("Produto não encontrado", Status.NOT_FOUND);
        }
        estoqueRepository.delete(produtoDeletado);
    }
}
