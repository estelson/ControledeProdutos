package com.exemplo.controledeprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FormProdutoActivity extends AppCompatActivity {

    private EditText edit_produto;
    private EditText edit_quantidade;
    private EditText edit_valor;

    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produto);

        produtoDAO = new ProdutoDAO(this);

        edit_produto = findViewById(R.id.edit_produto);
        edit_quantidade = findViewById(R.id.edit_quantidade);
        edit_valor = findViewById(R.id.edit_valor);
    }

    public void salvarProduto(View view) {
        String nome = edit_produto.getText().toString();
        String quantidade = edit_quantidade.getText().toString();
        String valor = edit_valor.getText().toString();

        if(!nome.isEmpty()) {
            if(!quantidade.isEmpty()) {
                int qtd = Integer.parseInt(quantidade);
                if(qtd >= 1) {
                    if(!valor.isEmpty()) {
                        double vlrProduto = Double.parseDouble(valor);
                        if(vlrProduto > 0) {
                            Produto produto = new Produto(nome, qtd, vlrProduto);

                            produtoDAO.salvarProduto(produto);
                        } else {
                            edit_valor.requestFocus();
                            edit_valor.setError("Informe um valor maior que 0");
                        }
                    } else {
                        edit_valor.requestFocus();
                        edit_valor.setError("Informe o valor do produto");
                    }
                } else {
                    edit_quantidade.requestFocus();
                    edit_quantidade.setError("Informe uma quantidade maior que 0");
                }
            } else {
                edit_quantidade.requestFocus();
                edit_quantidade.setError("Informe uma quantidade válida");
            }
        } else {
            edit_produto.requestFocus();
            edit_produto.setError("Informe o nome do produto");
        }
    }

}