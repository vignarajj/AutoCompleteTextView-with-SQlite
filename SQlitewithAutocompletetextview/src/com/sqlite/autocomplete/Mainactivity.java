package com.sqlite.autocomplete;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Mainactivity extends Activity
{
	private SQLiteAdapter db;
	AutoCompleteTextView at;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        at=(AutoCompleteTextView)findViewById(R.id.autotext);
        at.setThreshold(1);
        db= new SQLiteAdapter(getBaseContext());
        db.openToWrite();
        db.insert("apple");
        db.insert("battle");
        db.insert("cinema");
        db.insert("dalvik");
        db.insert("agent45");
        db.insert("alvin");
        db.insert("author23");
        db.insert("barcode");
        db.insert("begins");
        db.insert("below");
        db.close();
        db=new SQLiteAdapter(getBaseContext());
        db.openToRead();
        String[] getcontent=db.getAllContents();
        for(int i = 0; i < getcontent.length; i++)
        {
            Log.i(this.toString(), getcontent[i]);
        }
 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, getcontent);
        at.setAdapter(adapter);
        db.close();
    }
}