package com.exemplo.controledeprodutos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterProduto.OnClick {

    private AdapterProduto adapterProduto;
    private SwipeableRecyclerView rvProdutos;

    ImageButton ib_add;
    ImageButton ib_ver_mais;

    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        produtoDAO = new ProdutoDAO(this);

        ib_add = findViewById(R.id.ib_add);
        ib_ver_mais = findViewById(R.id.ib_ver_mais);

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
                }

                return true;
            });

            popupMenu.show();
        });
    }

    private void configRecyclerView() {
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        rvProdutos.setHasFixedSize(true);
        adapterProduto = new AdapterProduto(produtoDAO.getListProdutos(), this);
        rvProdutos.setAdapter(adapterProduto);

        rvProdutos.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {

            }

            @Override
            public void onSwipedRight(int position) {
                produtoDAO.getListProdutos().remove(position);
                adapterProduto.notifyItemRemoved(position);
            }
        });
    }

    @Override
    public void onClickListener(Produto produto) {
        Intent intent = new Intent(this, FormProdutoActivity.class);
        intent.putExtra("produto", produto);

        startActivity(intent);
    }
}