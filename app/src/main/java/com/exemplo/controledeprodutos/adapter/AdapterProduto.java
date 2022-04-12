package com.exemplo.controledeprodutos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exemplo.controledeprodutos.R;
import com.exemplo.controledeprodutos.model.Produto;

import java.util.List;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewHolder> {

    private List<Produto> produtoList;
    private OnClick onClick;

    public AdapterProduto(List<Produto> produtoList, OnClick onClick) {
        this.produtoList = produtoList;
        this.onClick = onClick;
    }

    /**
     * Controla o onCreate da lista de produtos
     * ---------------------------------------------------------------------------------------------
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);

        return new MyViewHolder(itemView);
    }

    /**
     * Controla as informações de cada registro da lista de produto e suas respectivas informações
     * ---------------------------------------------------------------------------------------------
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Produto produto = produtoList.get(position);
        holder.text_produto.setText(produto.getNome());
        holder.text_estoque.setText("Estoque: " + produto.getEstoque());
        holder.text_valor.setText("R$ " + produto.getValor());

        holder.itemView.setOnClickListener(view -> onClick.onClickListener(produto));
    }

    /**
     * Controla a contagem de registros na lista
     * ---------------------------------------------------------------------------------------------
     * @return
     */
    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    public interface OnClick {
        void onClickListener(Produto produto);
    }

    /**
     * Holder  para controlar o comportamento do adapter de produtos,  configurando quais  elementos
     * teremos na lista de produtos
     * ---------------------------------------------------------------------------------------------
     */
    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_produto;
        TextView text_estoque;
        TextView text_valor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text_produto = itemView.findViewById(R.id.text_produto);
            text_estoque = itemView.findViewById(R.id.text_estoque);
            text_valor = itemView.findViewById(R.id.text_valor);
        }
    }

}
