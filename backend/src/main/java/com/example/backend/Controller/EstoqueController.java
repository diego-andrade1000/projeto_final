package com.example.backend.Controller;


import com.example.backend.Domain.Estoque;
import com.example.backend.Service.EstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Timed;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/estoque")
@Transactional
public class EstoqueController{

    private final EstoqueService estoqueService;

    public EstoqueController(
            EstoqueService estoqueService
    ){
        this.estoqueService = estoqueService;
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> getAll() {
        List<Estoque> produtos = estoqueService.getAll();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Estoque> save(@RequestBody Estoque produto){
        estoqueService.save(produto);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> update(@PathVariable("id") Long id ,@RequestBody Estoque produto){
        estoqueService.update(id, produto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        estoqueService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
