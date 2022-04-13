package com.exemplo.controledeprodutos.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.exemplo.controledeprodutos.R;
import com.exemplo.controledeprodutos.activity.MainActivity;
import com.exemplo.controledeprodutos.helper.FirebaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText edit_email;
    private EditText edit_senha;

    private TextView text_criar_conta;
    private TextView text_recuperar_conta;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarComponentes();

        configCliques();
    }

    public void logar(View view) {
        String email = edit_email.getText().toString().trim();
        String senha = edit_senha.getText().toString();

        if(!email.isEmpty()) {
            if(!senha.isEmpty()) {
                progressBar.setVisibility(View.VISIBLE);

                validarLogin(email, senha);
            } else {
                edit_senha.requestFocus();
                edit_senha.setError("Informe sua senha");
            }
        } else {
            edit_email.requestFocus();
            edit_email.setError("Informe seu e-Mail");
        }
    }

    private void iniciarComponentes() {
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);

        text_criar_conta = findViewById(R.id.text_criar_conta);
        text_recuperar_conta = findViewById(R.id.text_recuperar_conta);

        progressBar = findViewById(R.id.progressBar);
    }

    private void configCliques() {
        text_criar_conta.setOnClickListener(view -> {
            startActivity(new Intent(this, CriarContaActivity.class));
        });

        text_recuperar_conta.setOnClickListener(view -> {
            startActivity(new Intent(this, RecuperarContaActivity.class));
        });
    }

    private void validarLogin(String email, String senha) {
        FirebaseHelper.getAuth().signInWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                finish();

                startActivity(new Intent(this, MainActivity.class));
            } else {
                String error = task.getException().getMessage();
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

                progressBar.setVisibility(View.GONE);
            }
        });
    }

}