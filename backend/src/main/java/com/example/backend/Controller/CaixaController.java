package com.example.backend.Controller;


import com.example.backend.Domain.Caixa;
import com.example.backend.Service.CaixaService;
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
@RequestMapping("/api/caixa")
public class CaixaController {

    private final CaixaService caixaService;

    public CaixaController(
            CaixaService caixaService
    ){
        this.caixaService = caixaService;
    }

    @GetMapping
    public ResponseEntity<List<Caixa>> getAll() {
        List<Caixa> caixas = caixaService.getAll();
        return ResponseEntity.ok(caixas);
    }

    @PostMapping
    public ResponseEntity<Caixa> save(@RequestBody Caixa caixa){
        caixaService.save(caixa);
        return ResponseEntity.ok(caixa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caixa> update(@PathVariable("id") Long id ,@RequestBody Caixa caixa){
        caixaService.update(id, caixa);
        return ResponseEntity.ok(caixa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        caixaService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
