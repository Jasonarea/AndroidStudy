package com.example.hyeon.part3_8;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReadDBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_db);

        TextView titleView = (TextView)findViewById(R.id.read_title);
        TextView contentview = (TextView)findViewById(R.id.read_content);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select title, content from tb_memo order by _id desc limit 1", null);
        while(cursor.moveToNext()) {
            titleView.setText(cursor.getString(0));
            contentview.setText(cursor.getString(1));
        }
        db.close();
    }
}
