package com.exemplo.controledeprodutos;

import java.io.Serializable;

public class Produto implements Serializable {

    private int id;
    private String nome;
    private int estoque;
    private double valor;

    public Produto() {
    }

    public Produto(String nome, int estoque, double valor) {
        this.nome = nome;
        this.estoque = estoque;
        this.valor = valor;
    }

    public Produto(int id, String nome, int estoque, double valor) {
        this.id = id;
        this.nome = nome;
        this.estoque = estoque;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
