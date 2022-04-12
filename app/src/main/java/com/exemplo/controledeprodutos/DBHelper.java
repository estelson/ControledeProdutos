package com.exemplo.controledeprodutos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME_DB = "DB_APP";
    public static final String TB_PRODUTO = "TB_PRODUTO";

    public DBHelper(Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS ";
        sql += TB_PRODUTO + " (";
        sql += "id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += "nome TEXT NOT NULL, ";
        sql += "estoque INTEGER NOT NULL, ";
        sql += "valor DOUBLE NOT NULL);";

        try {
            sqLiteDatabase.execSQL(sql);
        } catch (Exception e) {
            Log.i("ERROR", "Erro ao criar a tabela " + TB_PRODUTO + ": " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
