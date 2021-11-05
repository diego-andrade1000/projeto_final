package com.example.backend.Domain;


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

@Getter
@Setter
@Entity
@Table(name = "estoque")
@SequenceGenerator(name = "est_seq", sequenceName = "estoque_seq", initialValue = 1, allocationSize = 1)
public class Estoque implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "est_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_do_produto")
    private String nome;

    @Column(name = "quantidade")
    private Long quantidade;

    @Column(name = "valor")
    private String valor;

    public Estoque(){};

    public Estoque(
            String nome,
            Long quantidade,
            String valor
    ){
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }

}
