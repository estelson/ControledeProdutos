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
    private List<Produto> produtoList = new ArrayList<>();
    private SwipeableRecyclerView rvProdutos;

    ImageButton ib_add;
    ImageButton ib_ver_mais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ib_add = findViewById(R.id.ib_add);
        ib_ver_mais = findViewById(R.id.ib_ver_mais);

        rvProdutos = findViewById(R.id.rvProdutos);

        carregaLista();

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
        adapterProduto = new AdapterProduto(produtoList, this);
        rvProdutos.setAdapter(adapterProduto);

        rvProdutos.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {

            }

            @Override
            public void onSwipedRight(int position) {
                produtoList.remove(position);
                adapterProduto.notifyItemRemoved(position);
            }
        });
    }

    private void carregaLista() {
        Produto  produto1 = new Produto("Monitor LG 34" + '\u0022', 45, 1349.99);
        Produto  produto2 = new Produto("Caixa de som C3 Tech", 15, 149.99);
        Produto  produto3 = new Produto("Microfone Blue yeti", 38, 1699.99);
        Produto  produto4 = new Produto("Gabinete NZXT M440", 15, 979.99);
        Produto  produto5 = new Produto("Placa Mãe Asus", 60, 1199.99);
        Produto  produto6 = new Produto("Memória Corsair 16GB", 44, 599.99);

        produtoList.add(produto1);
        produtoList.add(produto2);
        produtoList.add(produto3);
        produtoList.add(produto4);
        produtoList.add(produto5);
        produtoList.add(produto6);

        produtoList.add(produto1);
        produtoList.add(produto2);
        produtoList.add(produto3);
        produtoList.add(produto4);
        produtoList.add(produto5);
        produtoList.add(produto6);

        produtoList.add(produto1);
        produtoList.add(produto2);
        produtoList.add(produto3);
        produtoList.add(produto4);
        produtoList.add(produto5);
        produtoList.add(produto6);
    }

    @Override
    public void onClickListener(Produto produto) {
        Toast.makeText(this, produto.getNome(), Toast.LENGTH_SHORT).show();
    }
}