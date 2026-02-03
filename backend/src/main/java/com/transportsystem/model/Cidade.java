package com.transportsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cidades")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cidade;

    private String nome_cidade;

    public void setNome(String nome) {
        this.nome_cidade = nome;
    }

    public String getNome(String nome) {
        this.nome_cidade = nome;
        return nome;
    }

}
