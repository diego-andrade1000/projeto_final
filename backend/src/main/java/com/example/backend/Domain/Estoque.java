package com.example.backend.Domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
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
