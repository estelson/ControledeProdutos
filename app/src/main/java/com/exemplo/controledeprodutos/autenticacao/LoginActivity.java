package com.exemplo.controledeprodutos.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.exemplo.controledeprodutos.R;

public class LoginActivity extends AppCompatActivity {

    private TextView text_criar_conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarComponentes();

        configCliques();
    }

    private void iniciarComponentes() {
        text_criar_conta = findViewById(R.id.text_criar_conta);
    }

    private void configCliques() {
        text_criar_conta.setOnClickListener(view -> {
            startActivity(new Intent(this, CriarContaActivity.class));
        });
    }

}