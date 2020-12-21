package com.example.lab11
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) : SQLiteOpenHelper(context,"mDataBase.db",null,1) {

    override fun onCreate(db: SQLiteDatabase) {
        //db.execSQL("CREATE TABLE myTable(book text PRIMARY KEY, price integer NOT NULL)")
        db.execSQL("CREATE TABLE myTable(book text PRIMARY KEY,price integer NOT NULL)")
    }

    override fun onUpgrade(db : SQLiteDatabase , i : Int , i1 : Int) {
        db.execSQL("DROP TABLE IF EXISTS myTable")
        onCreate(db)
    }

}
/*package com.example.lab11

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper internal constructor(context: Context) :
    SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE　myTable(book text PRIMARY KEY,price integer NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS myTable")
        onCreate(db)
    }

    companion object {
        private val name = "mdatabase.db"
        private val version = 1
    }
}*/