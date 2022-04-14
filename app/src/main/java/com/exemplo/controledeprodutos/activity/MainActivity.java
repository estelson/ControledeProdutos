package com.exemplo.controledeprodutos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.exemplo.controledeprodutos.adapter.AdapterProduto;
import com.exemplo.controledeprodutos.autenticacao.LoginActivity;
import com.exemplo.controledeprodutos.helper.FirebaseHelper;
import com.exemplo.controledeprodutos.model.Produto;
import com.exemplo.controledeprodutos.R;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterProduto.OnClick {

    private AdapterProduto adapterProduto;
    private List<Produto> produtoList = new ArrayList<>();
    private SwipeableRecyclerView rvProdutos;

    ImageButton ib_add;
    ImageButton ib_ver_mais;

    TextView text_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ib_add = findViewById(R.id.ib_add);
        ib_ver_mais = findViewById(R.id.ib_ver_mais);
        text_info = findViewById(R.id.text_info);
        rvProdutos = findViewById(R.id.rvProdutos);

        configRecyclerView();

        ouvinteCliques();
    }

    private void ouvinteCliques() {
        ib_add.setOnClickListener(view -> {
            startActivity(new Intent(this, FormProdutoActivity.class));
        });

        ib_ver_mais.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(this, ib_ver_mais);
            popupMenu.getMenuInflater().inflate(R.menu.menu_toolbar, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if(menuItem.getItemId() == R.id.menu_sobre) {
                    Toast.makeText(this, "Sobre...", Toast.LENGTH_SHORT).show();
                } else if(menuItem.getItemId() == R.id.menu_sair) {
                    FirebaseHelper.getAuth().signOut();
                    startActivity(new Intent(this, LoginActivity.class));
                }

                return true;
            });

            popupMenu.show();
        });
    }

    private void configRecyclerView() {
        produtoList.clear();

        verificarQtdLista();

        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        rvProdutos.setHasFixedSize(true);
        adapterProduto = new AdapterProduto(produtoList, this);
        rvProdutos.setAdapter(adapterProduto);

        rvProdutos.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {

            }

            @Override
            public void onSwipedRight(int position) {
                Produto produto = produtoList.get(position);
                produtoList.remove(produto);
                adapterProduto.notifyItemRemoved(position);

                verificarQtdLista();
            }
        });
    }

    private void verificarQtdLista() {
        if(produtoList.size() == 0) {
            text_info.setVisibility(View.VISIBLE);
        } else {
            text_info.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        configRecyclerView();
    }

    @Override
    public void onClickListener(Produto produto) {
        Intent intent = new Intent(this, FormProdutoActivity.class);
        intent.putExtra("produto", produto);

        startActivity(intent);
    }
}