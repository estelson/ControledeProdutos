package com.exemplo.controledeprodutos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ProdutoDAO {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public ProdutoDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);

        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void salvarProduto(Produto produto) {
        /**
         * ContentValues: É utilizado para persistência do banco de dados onde podemos mapear  chave
         * e valor dos registros salvos
         */
        ContentValues cv = new ContentValues();
        cv.put("nome", produto.getNome());
        cv.put("estoque", produto.getEstoque());
        cv.put("valor", produto.getValor());

        try {
            write.insert(DBHelper.TB_PRODUTO, null, cv);
            // write.close();
        } catch (Exception e) {
            Log.i("ERROR", "Erro ao salvar Produto: " + e.getMessage());
        }
    }

}
