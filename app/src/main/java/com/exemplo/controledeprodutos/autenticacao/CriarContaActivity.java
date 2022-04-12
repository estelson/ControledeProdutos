package com.exemplo.controledeprodutos.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.exemplo.controledeprodutos.R;

public class CriarContaActivity extends AppCompatActivity {

    private EditText edit_nome;
    private EditText edit_email;
    private EditText edit_senha;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        iniciarComponentes();

        configCliques();
    }

    private void iniciarComponentes() {
        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        progressBar = findViewById(R.id.progressBar);

        TextView text_titulo = findViewById(R.id.text_titulo);
        text_titulo.setText("Criar conta");
    }

    public void validarDados(View view) {
        String nome = edit_nome.getText().toString();
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        if(!nome.isEmpty()) {
            if(!email.isEmpty()) {
                if(!senha.isEmpty()) {
                    Toast.makeText(this, "Tudo certo!", Toast.LENGTH_SHORT).show();
                } else {
                    edit_senha.requestFocus();
                    edit_senha.setError("Informe sua senha");
                }
            } else {
                edit_email.requestFocus();
                edit_email.setError("Informe seu e-Mail");
            }
        } else {
            edit_nome.requestFocus();
            edit_nome.setError("Informe seu nome");
        }
    }

    private void configCliques() {
        findViewById(R.id.id_voltar).setOnClickListener(view -> {
            finish();
        });
    }

}