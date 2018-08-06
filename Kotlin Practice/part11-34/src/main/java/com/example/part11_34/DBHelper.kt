package com.example.part11_34

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "datadb", null, 1) {
    override fun onCreate(p0:SQLiteDatabase?) {
        val tablesql = "create table tb_data (" +
                "_id integer primary key autoincrement," +
                "name not null," +
                "date)"

        p0!!.execSQL(tablesql)  //non null

        p0!!.execSQL("insert into tb_data (name, date) values ('안영주', '2017-01-01')")
        p0!!.execSQL("insert into tb_data (name, date) values ('최은경', '2017-01-01')")
        p0!!.execSQL("insert into tb_data (name, date) values ('최호성', '2017-01-01')")
        p0!!.execSQL("insert into tb_data (name, date) values ('정성택', '2017-01-01')")
        p0!!.execSQL("insert into tb_data (name, date) values ('정길용', '2017-01-01')")
        p0!!.execSQL("insert into tb_data (name, date) values ('임윤정', '2017-01-01')")
        p0!!.execSQL("insert into tb_data (name, date) values ('김종덕', '2017-01-01')")
        p0!!.execSQL("insert into tb_data (name, date) values ('채규태', '2017-01-01')")
        p0!!.execSQL("insert into tb_data (name, date) values ('원형섭', '2017-01-01')")
        p0!!.execSQL("insert into tb_data (name, date) values ('표선영', '2017-01-01')")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        p0!!.execSQL("drop table tb_data")
        onCreate(p0)
    }
}