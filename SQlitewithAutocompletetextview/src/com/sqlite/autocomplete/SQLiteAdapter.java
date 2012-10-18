package com.sqlite.autocomplete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SQLiteAdapter {

public static final String MYDATABASE_NAME = "MY_DATABASE";
public static final String MYDATABASE_TABLE = "MY_TABLE";
public static final int MYDATABASE_VERSION = 1;
public static final String KEY_ID = "_id";
public static final String KEY_CONTENT = "Content";

//create table MY_DATABASE (ID integer primary key, Content text not null);
private static final String SCRIPT_CREATE_DATABASE =
"create table " + MYDATABASE_TABLE + " ("
+ KEY_ID + " integer primary key autoincrement, "
+ KEY_CONTENT + " text not null);";

private SQLiteHelper sqLiteHelper;
private SQLiteDatabase sqLiteDatabase;

private Context context;

public SQLiteAdapter(Context c){
context = c;
}

public SQLiteAdapter openToRead() throws android.database.SQLException {
sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
sqLiteDatabase = sqLiteHelper.getReadableDatabase();
return this;
}

public SQLiteAdapter openToWrite() throws android.database.SQLException {
sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
sqLiteDatabase = sqLiteHelper.getWritableDatabase();
return this;
}

public void close(){
sqLiteHelper.close();
}

public long insert(String content){

ContentValues contentValues = new ContentValues();
contentValues.put(KEY_CONTENT, content);
return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
}

public int deleteAll(){
return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
}

public Cursor queueAll(){
String[] columns = new String[]{KEY_ID, KEY_CONTENT};
Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
  null, null, null, null, null);

return cursor;
}
public String[] getAllContents()
{
    Cursor cursor = this.sqLiteDatabase.query(MYDATABASE_TABLE, new String[] {KEY_CONTENT}, null, null, null, null, null);

    if(cursor.getCount() >0)
    {
        String[] str = new String[cursor.getCount()];
        int i = 0;

        while (cursor.moveToNext())
        {
             str[i] = cursor.getString(cursor.getColumnIndex(KEY_CONTENT));
             i++;
         }
        return str;
    }
    else
    {
        return new String[] {};
    }
}
public class SQLiteHelper extends SQLiteOpenHelper {

public SQLiteHelper(Context context, String name,
  CursorFactory factory, int version) {
 super(context, name, factory, version);
}

@Override
public void onCreate(SQLiteDatabase db) {
 // TODO Auto-generated method stub
 db.execSQL(SCRIPT_CREATE_DATABASE);
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 // TODO Auto-generated method stub

}

}

}
