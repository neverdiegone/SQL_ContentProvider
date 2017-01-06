package com.example.nguyendinh.sql_contentprovider.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nguyendinh on 21/08/2016.
 */
public class DataHelper extends SQLiteOpenHelper {
    public static final String ID ="id";
    public static final String DIA_DIEM ="diadiem";
    public static final String QUOC_GIA ="quocgia";
    public static final String LATITUDE ="latitude";
    public static final String LONGITUDE ="longitude";

    public static final String TU_GIO ="tugio";
    public static final String NHIET_DO_CAO_NHAT ="nhietdocaonhat";
    public static final String NHIET_DO_THAP_NHAT ="nhietdothapnhat";
    public static final String DO_AM ="doam";
    public static final String MAY ="may";
    public static final String DEN_GIO ="dengio";

    public static final String TEN_BANG ="thoitiet";
    public static final String TEN_CSDL ="thoitietdb";
    public static final int PHIEN_BAN =1;

    private static final  String TAO_BANG = ""
            +"CREATE TABLE "+TEN_BANG+"("
            + ID + " integer primary key autoincrement, "
            + DIA_DIEM + " text, "
            + QUOC_GIA + " text, "
            + LATITUDE + " real, "
            + LONGITUDE +" real,"
            + TU_GIO + " integer, "
            + DEN_GIO + " integer, "
            + NHIET_DO_CAO_NHAT + " real, "
            + NHIET_DO_THAP_NHAT + " real, "
            + DO_AM + " real,"
            + MAY + " text)";



    public DataHelper(Context context) {
        super(context, TEN_CSDL, null, PHIEN_BAN);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TAO_BANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP if exists"+TEN_BANG);
        onCreate(db);
    }

}
