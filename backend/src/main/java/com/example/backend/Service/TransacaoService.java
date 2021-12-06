package com.example.backend.Service;

import com.example.backend.Domain.Caixa;
import com.example.backend.Domain.Estoque;
import com.example.backend.Domain.Transacao;
import com.example.backend.Exception.GenericHTTPException;
import com.example.backend.Repository.CaixaRepository;
import com.example.backend.Repository.EstoqueRepository;
import com.example.backend.Repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final EstoqueRepository estoqueRepository;
    private final CaixaService caixaService;
    private final CaixaRepository caixaRepository;

    public TransacaoService(
            TransacaoRepository transacaoRepository,
            EstoqueRepository estoqueRepository,
            CaixaService caixaService,
            CaixaRepository caixaRepository
    ){
        this.transacaoRepository = transacaoRepository;
        this.estoqueRepository = estoqueRepository;
        this.caixaService = caixaService;
        this.caixaRepository = caixaRepository;
    }

    public List<Transacao> getAll(){
        List<Transacao> transacoes = transacaoRepository.findAll();
//        if(transacoes == null || transacoes.isEmpty()){
//            throw new GenericHTTPException("Nenhuma transação encontrada", Status.NOT_FOUND);
//        }
        return transacoes;
    }

    public Transacao adicionarTransacao(Transacao transacao){
        Estoque produto = estoqueRepository.findByNome(transacao.getNomeDoProduto());
        var ultimoCaixa = caixaRepository.findTopByOrderByIdDesc();
        transacao.setData(LocalDateTime.now());
        if(transacao.getCompraOuVenda()){
            if(produto == null || produto.getQuantidade() == 0){
                throw new GenericHTTPException("Produto não encontrado em estoque", Status.NOT_FOUND);
            }
            transacaoProduto(transacao.getValor(), produto.getValor(), produto, transacao.getCompraOuVenda());
            Double valorTotal;
            if(ultimoCaixa !=null) {
                valorTotal = Double.parseDouble(ultimoCaixa.getTotal()) + Double.parseDouble(transacao.getValor());
            }else{
                valorTotal = 0 + Double.parseDouble(transacao.getValor());
            }
            caixaService.save(new Caixa(transacao.getValor(), null, String.valueOf(valorTotal), LocalDateTime.now()));
            transacaoRepository.save(transacao);
            return transacao;
        }else{
            if(produto == null){
                throw new GenericHTTPException("Produto ainda não cadastrado", Status.NOT_FOUND);
            }
            transacaoProduto(transacao.getValor(), produto.getValor(), produto, transacao.getCompraOuVenda());
            Double valorTotal;
            if(ultimoCaixa != null && Double.parseDouble(ultimoCaixa.getTotal()) >= Double.parseDouble(transacao.getValor())) {
                valorTotal = Double.parseDouble(ultimoCaixa.getTotal()) - Double.parseDouble(transacao.getValor());
            }else{
                throw new GenericHTTPException("Valor em caixa não suficiente", Status.UNAUTHORIZED);
            }
            caixaService.save(new Caixa(null , transacao.getValor(), String.valueOf(valorTotal), LocalDateTime.now()));
            transacaoRepository.save(transacao);
            return transacao;
        }
    }

    public void transacaoProduto(String valorTotal, String valorUnitario, Estoque produto, Boolean compraOuVenda){
        var numeroTotal = Double.parseDouble(valorTotal.replace(",", "."));
        var numeroUnitario = Double.parseDouble(valorUnitario.replace(",", "."));
        var quantidade = numeroTotal / numeroUnitario;
        if(compraOuVenda){
            if(produto.getQuantidade() >= quantidade){
                produto.setQuantidade(produto.getQuantidade() - (int)quantidade);
            }else{
                throw new GenericHTTPException("Produto insuficiente", Status.UNAUTHORIZED);
            }
        }else{
            produto.setQuantidade(produto.getQuantidade() + (int)quantidade);
        }
    }

    public Transacao update(Long id, Transacao transacao){
        var transacaoVelha = transacaoRepository.findById(id);
        if(transacaoVelha == null){
            throw new GenericHTTPException("Transacao não encontrada", Status.NOT_FOUND);
        }
        transacaoVelha.setCompraOuVenda(transacao.getCompraOuVenda());
        transacaoVelha.setNomeDoDistribuidor(transacao.getNomeDoDistribuidor());
        transacaoVelha.setNomeDoProduto(transacao.getNomeDoProduto());
        if(transacao.getValor().equals(transacaoVelha.getValor())){
            Estoque produto = estoqueRepository.findByNome(transacao.getNomeDoProduto());
            if(produto == null || produto.getQuantidade() == 0){
                throw new GenericHTTPException("Produto não encontrado em estoque", Status.NOT_FOUND);
            }
            var ultimoCaixa = caixaRepository.findTopByOrderByIdDesc();
            if(transacaoVelha.getCompraOuVenda()){
                var valorTotal = Double.parseDouble(ultimoCaixa.getTotal()) - Double.parseDouble(transacaoVelha.getValor());
                caixaService.update(ultimoCaixa.getId(), new Caixa(null ,transacaoVelha.getValor() , String.valueOf(valorTotal), LocalDateTime.now()));
                var valorNovoTotal = Double.parseDouble(ultimoCaixa.getTotal()) + Double.parseDouble(transacao.getValor());
                caixaService.update(ultimoCaixa.getId(), new Caixa(null, transacao.getValor(), String.valueOf(valorNovoTotal), LocalDateTime.now()));
            }else{
                var valorTotal = Double.parseDouble(ultimoCaixa.getTotal()) + Double.parseDouble(transacaoVelha.getValor());
                caixaService.update(ultimoCaixa.getId(), new Caixa(transacaoVelha.getValor(), null, String.valueOf(valorTotal), LocalDateTime.now()));
                var valorNovoTotal = Double.parseDouble(ultimoCaixa.getTotal()) - Double.parseDouble(transacao.getValor());
                caixaService.update(ultimoCaixa.getId(), new Caixa(transacao.getValor(), null, String.valueOf(valorNovoTotal), LocalDateTime.now()));
            }
            transacaoProduto(transacaoVelha.getValor(), produto.getValor(), produto, !transacaoVelha.getCompraOuVenda());
            transacaoProduto(transacao.getValor(), produto.getValor(), produto, transacao.getCompraOuVenda());
        }
        transacaoVelha.setValor(transacao.getValor());
        return transacao;
    }

    public void delete(Long id){
        var produtoDeletado = transacaoRepository.findById(id);
        if(produtoDeletado == null){
            throw new GenericHTTPException("Transação não encontrada", Status.NOT_FOUND);
        }
        transacaoRepository.delete(produtoDeletado);
    }
}
