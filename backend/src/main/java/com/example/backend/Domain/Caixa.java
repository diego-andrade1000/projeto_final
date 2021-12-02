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
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "caixa")
@SequenceGenerator(name = "cai_seq", sequenceName = "caixa_seq", initialValue = 1, allocationSize = 1)
public class Caixa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cai_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "data")
    LocalDateTime data;

    @Column(name = "valor_entrada")
    private String valorEntrada;

    @Column(name = "valor_saida")
    private String valorSaida;

    @Column(name = "total")
    private String total;

    public Caixa(){}

    public Caixa(String valorEntrada, String valorSaida, String total, LocalDateTime data){
        this.valorEntrada = valorEntrada;
        this.valorSaida = valorSaida;
        this.total = total;
        this.data = data;
    }


}
