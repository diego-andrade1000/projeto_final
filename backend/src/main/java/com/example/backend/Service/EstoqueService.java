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
}
