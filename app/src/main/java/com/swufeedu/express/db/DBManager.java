package com.swufeedu.express.db;


import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DBHelper dbHelper;
    private String TBNAME;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    public void add(InfoItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("curname", item.getCurName());
        values.put("curnum", item.getCurNum());
        values.put("curstate", item.getCurState());
        db.insert(TBNAME, null, values);
        db.close();
    }

    public void addAll(List<InfoItem> list){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (InfoItem item : list) {
            ContentValues values = new ContentValues();
            values.put("curname", item.getCurName());
            values.put("curnum", item.getCurNum());
            values.put("curstate", item.getCurState());
            db.insert(TBNAME, null, values);
        }
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
    }

    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void update(InfoItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("curname", item.getCurName());
        values.put("curnum", item.getCurNum());
        values.put("curstate", item.getCurState());
        db.update(TBNAME, values, "ID=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    @SuppressLint("Range")
    public List<InfoItem> listAll(){
        List<InfoItem> infoList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if(cursor!=null){
            infoList = new ArrayList<InfoItem>();
            while(cursor.moveToNext()){
                InfoItem item = new InfoItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurName(cursor.getString(cursor.getColumnIndex("CURNAME")));
                item.setCurNum(cursor.getString(cursor.getColumnIndex("CURNUM")));
                item.setCurState(cursor.getString(cursor.getColumnIndex("CURSTATE")));

                infoList.add(item);
            }
            cursor.close();
        }
        db.close();
        return infoList;

    }

    @SuppressLint("Range")
    public InfoItem findById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, "ID=?", new String[]{String.valueOf(id)}, null, null, null);
        InfoItem infoItem = null;
        if(cursor!=null && cursor.moveToFirst()){
            infoItem = new InfoItem();
            infoItem.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            infoItem.setCurName(cursor.getString(cursor.getColumnIndex("CURNAME")));
            infoItem.setCurNum(cursor.getString(cursor.getColumnIndex("CURNUM")));
            infoItem.setCurState(cursor.getString(cursor.getColumnIndex("CURSTATE")));
            cursor.close();
        }
        db.close();
        return infoItem;
    }
}

