package com.example.backend.Repository;

import com.example.backend.Domain.Distribuidor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistribuidorRepository extends JpaRepository<Distribuidor, String> {
    List<Distribuidor> findAll();
    Distribuidor findById(Long id);
}
