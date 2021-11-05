package com.example.backend.Service;

import com.example.backend.Domain.Estoque;
import com.example.backend.Repository.EstoqueRepository;
import org.springframework.stereotype.Service;

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
        return estoqueRepository.findAll();
    }

    public Estoque save(Estoque produto){
        estoqueRepository.save(produto);
        return produto;
    }

    public Estoque update(Long id, Estoque produto){
        var produtoVelho = estoqueRepository.findById(id);
        produtoVelho.setNome(produto.getNome());
        produtoVelho.setQuantidade(produto.getQuantidade());
        produtoVelho.setValor(produto.getValor());
        return produto;
    }

    public void delete(Long id){
        var produtoDeletado = estoqueRepository.findById(id);
        estoqueRepository.delete(produtoDeletado);
    }
}
