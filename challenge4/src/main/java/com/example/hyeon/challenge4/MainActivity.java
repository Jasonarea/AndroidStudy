package com.example.hyeon.challenge4;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean callPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            callPermission = true;
        }

        if(!callPermission) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 200);
        }

        ListView listView = (ListView)findViewById(R.id.main_list);
        ArrayList<CallerVO> datas = new ArrayList<>();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select name, photo, date, phone from tb_calllog", null);
        while(cursor.moveToNext()) {
            CallerVO vo = new CallerVO();
            vo.name = cursor.getString(0);
            vo.photo = cursor.getString(1);
            vo.date = cursor.getString(2);
            vo.phone = cursor.getString(3);
            datas.add(vo);
        }
        db.close();

        CallLogAdapter adapter = new CallLogAdapter(this, R.layout.main_list_item, datas);
        listView.setAdapter(adapter);
    }
}
