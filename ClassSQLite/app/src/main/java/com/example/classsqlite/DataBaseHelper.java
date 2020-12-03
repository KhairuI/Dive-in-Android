package com.example.classsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String databaseName="player.db";
    public static final String tableName= "player_info";
    public static final String playerId="_id";
    public static final String playerCode="code";
    public static final String playerName="name";
    public static final String playerType="type";
    public static final int versionNo = 3;
    public static final String createTable=" CREATE TABLE "+tableName+"("+playerId+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ""+playerName+" VARCHAR(100), "+playerType+" VARCHAR(100), "+playerCode+" INTEGER); ";

    public static final String upgradeTable=" DROP TABLE IF EXISTS "+tableName;
    public static final String displayTable=" SELECT * FROM "+tableName;
    private Context context;

    public DataBaseHelper(Context context) {
        super(context, databaseName, null, versionNo);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(createTable);
            Toast.makeText(context, "Table created", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            db.execSQL(upgradeTable);
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();

        }
    }

    public long insert(String name,String type,String code){

        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(playerName,name);
        contentValues.put(playerType,type);
        contentValues.put(playerCode,code);
        long value= sqLiteDatabase.insert(tableName,null,contentValues);

        return value;
    }

    public Cursor show(){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(displayTable,null);
        return cursor;
    }

    public int delete(String id){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        int value= sqLiteDatabase.delete(tableName,playerId+" = ?",new String[]{id});
        return value;
    }

    public long update(String id,String name, String code, String type){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(playerId,id);
        contentValues.put(playerName,name);
        contentValues.put(playerType,type);
        contentValues.put(playerCode,code);

        long value= sqLiteDatabase.update(tableName,contentValues,playerId+" = ?",new String[]{id});
        return value;

    }
}
