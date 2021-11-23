package com.example.backend.Repository;

import com.example.backend.Domain.Caixa;
import com.example.backend.Domain.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaixaRepository extends JpaRepository<Caixa, String> {
    List<Caixa> findAll();
    Caixa findById(Long id);
    Caixa findTopByOrderByIdDesc();
}
