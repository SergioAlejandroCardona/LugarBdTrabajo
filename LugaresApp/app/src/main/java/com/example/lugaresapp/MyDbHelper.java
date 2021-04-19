package com.example.lugaresapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "lugares.db";

    public MyDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table lugares (Id INTEGER PRIMARY KEY, Nombre TEXT, Poblacion INTEGER, Latitud INTEGER, Longitud INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists lugares");
        onCreate(db);
    }
    public void InsertLugar(SQLiteDatabase db, Lugares lugares){
        ContentValues values= new ContentValues();
        values.put("Id", lugares.getId());
        values.put("Nombre", lugares.getNombre());
        values.put("Poblacion", lugares.getPoblacion());
        values.put("Latitud", lugares.getLatitud());
        values.put("Longitud", lugares.getLongitud());
        db.insert("lugares",null, values);
    }

    public ArrayList<Lugares> selectLugar(SQLiteDatabase db){
        ArrayList<Lugares> lugares = new ArrayList<Lugares>();
        Cursor filas = db.rawQuery("Select * from lugares", null);
        if (filas.moveToFirst()){
            do{
                Lugares lugar = new Lugares(filas.getInt(0), filas.getString(1),
                        filas.getInt(2), filas.getInt(3), filas.getInt(4));
                lugares.add(lugar);
            }while (filas.moveToNext());
        }
        return lugares;
    }

}
