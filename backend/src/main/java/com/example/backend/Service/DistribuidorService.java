package com.example.backend.Service;

import com.example.backend.Domain.Distribuidor;
import com.example.backend.Exception.GenericHTTPException;
import com.example.backend.Repository.DistribuidorRepository;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import java.util.List;

@Service
public class DistribuidorService {

    private final DistribuidorRepository distribuidorRepository;

    public DistribuidorService(
            DistribuidorRepository distribuidorRepository
    ){
        this.distribuidorRepository = distribuidorRepository;
    }

    public List<Distribuidor> getAll(){
        List<Distribuidor> distribuidores = distribuidorRepository.findAll();
        // if(distribuidores == null || distribuidores.isEmpty()){
        //     throw new GenericHTTPException("Nenhum distribuidor encontrado", Status.NOT_FOUND);
        // }
        return distribuidores;
    }

    public Distribuidor save(Distribuidor distribuidor){
        distribuidorRepository.save(distribuidor);
        return distribuidor;
    }

    public Distribuidor update(Long id, Distribuidor distribuidor){
        var distribuidorVelho = distribuidorRepository.findById(id);
        if(distribuidorVelho == null){
            throw new GenericHTTPException("distribuidor não encontrado", Status.NOT_FOUND);
        }
        distribuidorVelho.setNome(distribuidor.getNome());
        distribuidorVelho.setEmail(distribuidor.getEmail());
        distribuidorVelho.setRegistro(distribuidor.getRegistro());
        distribuidorVelho.setTelefone(distribuidor.getTelefone());
        return distribuidor;
    }

    public void delete(Long id){
        var distribuidorDeletado = distribuidorRepository.findById(id);
        if(distribuidorDeletado == null){
            throw new GenericHTTPException("distribuidor não encontrado", Status.NOT_FOUND);
        }
        distribuidorRepository.delete(distribuidorDeletado);
    }
}
