package com.zdq.buydemo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class BuyDemoDatabase {

    private static final String DB_NAME = "BuyDemoDatabase";
    private static final int DB_VERSION = 1;
    private static BuyDemoDatabase buyDemoDatabase;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public BuyDemoDatabase(Context context) {
        BuyDemoDatabaseOpenHelper helper = new BuyDemoDatabaseOpenHelper(context, DB_NAME, null, DB_VERSION);
        sqLiteDatabase = helper.getWritableDatabase();
        this.context = context;
    }

    public synchronized static BuyDemoDatabase getInstance(Context context){
        if(null == buyDemoDatabase){
            buyDemoDatabase = new BuyDemoDatabase(context);
        }
        return buyDemoDatabase;
    }

    public void insertUser(String username,String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        sqLiteDatabase.insert("user_table",null,contentValues);
        sqLiteDatabase.close();
    }

    public boolean isExistThisUser(String username){
        String myun;
        Cursor cursor = sqLiteDatabase.query("user_table",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                myun = cursor.getString(cursor.getColumnIndex("username"));
                if (username.equals(myun)){
                    return true;
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    public boolean isPasswordRight(String username,String password){
        Cursor cursor = sqLiteDatabase.query("user_table", null, "username like ?", new String[]{username}, null, null, null);
        String mypass;
        if (cursor.moveToFirst()){
            do {
                mypass = cursor.getString(cursor.getColumnIndex("password"));
                if (mypass.equals(password)){
                    return true;
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  false;
    }

}
