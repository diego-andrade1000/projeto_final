package com.example.backend.Controller;


import com.example.backend.Domain.Estoque;
import com.example.backend.Domain.Transacao;
import com.example.backend.Service.EstoqueService;
import com.example.backend.Service.TransacaoService;
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
@RequestMapping("/api/transacao")
public class TransacaoController {

    private final TransacaoService transacaoSerivce;

    public TransacaoController(
            TransacaoService transacaoSerivce
    ){
        this.transacaoSerivce = transacaoSerivce;
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> getAll() {
        List<Transacao> transacoes = transacaoSerivce.getAll();
        return ResponseEntity.ok(transacoes);
    }

    @PostMapping
    public ResponseEntity<Transacao> adicionarTransacao(@RequestBody Transacao transacao){
        transacaoSerivce.adicionarTransacao(transacao);
        return ResponseEntity.ok(transacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transacao> update(@PathVariable("id") Long id ,@RequestBody Transacao transacao){
        transacaoSerivce.update(id, transacao);
        return ResponseEntity.ok(transacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        transacaoSerivce.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
