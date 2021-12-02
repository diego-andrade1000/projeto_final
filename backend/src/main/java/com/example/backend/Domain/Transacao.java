package com.example.backend.Domain;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transacao")
@SequenceGenerator(name = "tra_seq", sequenceName = "transacao_seq", initialValue = 1, allocationSize = 1)
public class Transacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tra_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "compra_ou_venda")
    private Boolean compraOuVenda;

    @Column(name = "nome_do_produto")
    private String nomeDoProduto;

    @Column(name = "nome_do_distribuidor")
    private String nomeDoDistribuidor;

    @Column(name = "data")
    LocalDateTime data;

    @Column(name = "valor")
    String valor;

    public Transacao(){};

}
