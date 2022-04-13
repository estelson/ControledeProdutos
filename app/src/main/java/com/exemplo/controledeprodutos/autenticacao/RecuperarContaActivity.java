package com.exemplo.controledeprodutos.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.exemplo.controledeprodutos.R;
import com.exemplo.controledeprodutos.helper.FirebaseHelper;

public class RecuperarContaActivity extends AppCompatActivity {

    private EditText edit_email;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_conta);

        iniciarComponentes();
    }

    public void recuperarSenha(View view) {
        String email = edit_email.getText().toString().trim();
        if(!email.isEmpty()) {
            progressBar.setVisibility(View.VISIBLE);

            enviarEmail(email);
        } else {
            edit_email.requestFocus();
            edit_email.setError("Informe seu e-Mail");
        }
    }

    private void  enviarEmail(String email) {
        FirebaseHelper.getAuth().sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                Toast.makeText(this, "e-Mail de recuperação de senha enviado para " + email, Toast.LENGTH_LONG).show();

                progressBar.setVisibility(View.GONE);
            } else {
                String error = task.getException().getMessage();
                Toast.makeText(this, "Erro ao enviar e-Mail: " + error, Toast.LENGTH_LONG).show();

                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void iniciarComponentes() {
        edit_email = findViewById(R.id.edit_email);
        progressBar = findViewById(R.id.progressBar);
    }

}