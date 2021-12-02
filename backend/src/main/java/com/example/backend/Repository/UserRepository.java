package com.example.backend.Repository;

import com.example.backend.Domain.Estoque;
import com.example.backend.Domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, String> {
    List<Transacao> findAll();
    Transacao findById(Long id);
}
