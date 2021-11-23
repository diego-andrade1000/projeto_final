package com.example.backend.Controller;


import com.example.backend.Domain.Distribuidor;
import com.example.backend.Service.DistribuidorService;
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
@RequestMapping("/api/distribuidor")
public class DistribuidorController {

    private final DistribuidorService distribuidorSerivce;

    public DistribuidorController(
            DistribuidorService distribuidorSerivce
    ){
        this.distribuidorSerivce = distribuidorSerivce;
    }

    @GetMapping
    public ResponseEntity<List<Distribuidor>> getAll() {
        List<Distribuidor> distribuidor = distribuidorSerivce.getAll();
        return ResponseEntity.ok(distribuidor);
    }

    @PostMapping
    public ResponseEntity<Distribuidor> save(@RequestBody Distribuidor distribuidor){
        distribuidorSerivce.save(distribuidor);
        return ResponseEntity.ok(distribuidor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Distribuidor> update(@PathVariable("id") Long id ,@RequestBody Distribuidor distribuidor){
        distribuidorSerivce.update(id, distribuidor);
        return ResponseEntity.ok(distribuidor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        distribuidorSerivce.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
