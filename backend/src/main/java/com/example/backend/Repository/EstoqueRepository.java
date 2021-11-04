package com.example.backend.Repository;

import com.example.backend.Domain.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, String> {
    List<Estoque> findAll();
}
