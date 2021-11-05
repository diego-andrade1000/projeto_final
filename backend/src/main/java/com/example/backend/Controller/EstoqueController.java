package com.example.backend.Controller;


import com.example.backend.Domain.Estoque;
import com.example.backend.Service.EstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController{

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

    @PostMapping
    public ResponseEntity<Estoque> save(@RequestBody Estoque produto){
        estoqueSerivce.save(produto);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> update(@PathVariable("id") Long id ,@RequestBody Estoque produto){
        estoqueSerivce.update(id, produto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        estoqueSerivce.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
