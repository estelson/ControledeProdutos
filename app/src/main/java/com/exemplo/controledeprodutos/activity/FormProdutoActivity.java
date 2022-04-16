package com.exemplo.controledeprodutos.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.exemplo.controledeprodutos.model.Produto;
import com.exemplo.controledeprodutos.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

public class FormProdutoActivity extends AppCompatActivity {

    private static final int REQUEST_GALERIA = 100;

    private ImageView imagem_produto;
    private EditText edit_produto;
    private EditText edit_quantidade;
    private EditText edit_valor;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produto);

        iniciarComponentes();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            produto = (Produto) bundle.getSerializable("produto");

            editarProduto();
        }
    }

    private void iniciarComponentes() {
        edit_produto = findViewById(R.id.edit_produto);
        edit_quantidade = findViewById(R.id.edit_quantidade);
        edit_valor = findViewById(R.id.edit_valor);
        imagem_produto = findViewById(R.id.imagem_produto);
    }

    public void abrirGaleria(View view) {
        verificarPermissaoGaleria();
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALERIA);
    }

    private void showDialogPermissao(PermissionListener listener, String[] permissoes) {
        TedPermission.create()
                .setPermissionListener(listener)
                .setDeniedTitle("Permissões")
                .setDeniedMessage("Você negou a permissão para acessar a galeria do dispositivo. Deseja permitir?")
                .setDeniedCloseButtonText("Não")
                .setGotoSettingButtonText("Sim")
                .setPermissions(permissoes)
                .check();
    }

    private void verificarPermissaoGaleria() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                abrirGaleria();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(FormProdutoActivity.this, "Permissão negada", Toast.LENGTH_SHORT).show();
            }
        };

        showDialogPermissao(permissionListener, new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE
        });
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
                            if(produto == null) {
                                produto = new Produto();
                            }

                            //Produto produto = new Produto(nome, qtd, vlrProduto);
                            produto.setNome(nome);
                            produto.setEstoque(qtd);
                            produto.setValor(vlrProduto);

                            produto.salvarProduto();

                            Toast.makeText(this, "Produto '" + nome + "' gravado com sucesso", Toast.LENGTH_SHORT).show();

                            // Grava o registro e volta para a lista de produtos
                            finish();
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

    private void editarProduto() {
        edit_produto.setText(produto.getNome());
        edit_quantidade.setText(String.valueOf(produto.getEstoque()));
        edit_valor.setText(String.valueOf(produto.getValor()));
    }

}