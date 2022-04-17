package com.exemplo.controledeprodutos.model;

import com.exemplo.controledeprodutos.helper.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

public class Produto implements Serializable {

    private String id;
    private String nome;
    private int estoque;
    private double valor;
    private String urlImagem;


    public Produto() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference();
        this.setId(reference.push().getKey()); // Gera um ID automático para o produto no Realtime Database e recupera ele
    }

    public Produto(String nome, int estoque, double valor) {
        this.nome = nome;
        this.estoque = estoque;
        this.valor = valor;
    }

    public Produto(String id, String nome, int estoque, double valor) {
        this.id = id;
        this.nome = nome;
        this.estoque = estoque;
        this.valor = valor;
    }

    public void salvarProduto() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference()
                .child("produtos")
                .child(FirebaseHelper.getUIDFirebase())
                .child(this.id);

        reference.setValue(this);
    }

    public void excluirProduto() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference()
                .child("produtos")
                .child(FirebaseHelper.getUIDFirebase())
                .child(this.id);

        reference.removeValue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
