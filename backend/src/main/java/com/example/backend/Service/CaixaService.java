package com.example.backend.Service;

import com.example.backend.Domain.Caixa;
import com.example.backend.Exception.GenericHTTPException;
import com.example.backend.Repository.CaixaRepository;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import java.util.List;

@Service
public class CaixaService {

    private final CaixaRepository caixaRepository;

    public CaixaService(
            CaixaRepository caixaRepository
    ){
        this.caixaRepository = caixaRepository;
    }

    public List<Caixa> getAll(){
        List<Caixa> caixas = caixaRepository.findAll();
        if(caixas == null || caixas.isEmpty()){
            throw new GenericHTTPException("Nenhum caixa encontrado", Status.NOT_FOUND);
        }
        return caixas;
    }

    public Caixa save(Caixa caixa){
        caixaRepository.save(caixa);
        return caixa;
    }

    public Caixa update(Long id, Caixa caixa){
        var caixaVelho = caixaRepository.findById(id);
        if(caixaVelho == null){
            throw new GenericHTTPException("caixa não encontrado", Status.NOT_FOUND);
        }
        caixaVelho.setValorEntrada(caixa.getValorEntrada());
        caixaVelho.setValorSaida(caixa.getValorSaida());
        caixaVelho.setTotal(caixa.getTotal());
        return caixa;
    }

    public void delete(Long id){
        var caixaDeletado = caixaRepository.findById(id);
        if(caixaDeletado == null){
            throw new GenericHTTPException("caixa não encontrado", Status.NOT_FOUND);
        }
        caixaRepository.delete(caixaDeletado);
    }
}
