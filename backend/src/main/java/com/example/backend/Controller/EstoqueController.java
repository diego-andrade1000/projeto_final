package com.example.backend.Controller;


import com.example.backend.Domain.Estoque;
import com.example.backend.Service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

    private final EstoqueService estoqueSerivce;

    public EstoqueController(
            EstoqueService estoqueSerivce
    ){
        this.estoqueSerivce = estoqueSerivce;
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> getAll() {
        List<Estoque> produtos = estoqueSerivce.getAll();
        return ResponseEntity.ok(produtos);
    }
}
