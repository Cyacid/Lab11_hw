package com.example.lab11_hw

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.lab11.MyDBHelper
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var dbrw = MyDBHelper(this).writableDatabase
        val items = ArrayList<String>()
        val edBook : EditText = findViewById(R.id.bookname)
        val edPrice : EditText = findViewById(R.id.price)
        val btnNew : Button = findViewById(R.id.btn_new)
        val btnFind : Button = findViewById(R.id.btn_find)
        val btnUpdate : Button = findViewById(R.id.btn_update)
        val btnDel : Button = findViewById(R.id.btn_del)
        val list : ListView = findViewById(R.id.listview)
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items)
        list.adapter = adapter


        btnFind.setOnClickListener{
            val c: Cursor
            when{
                edBook.length() < 1 -> c = dbrw.rawQuery("SELECT * FROM myTable",null)
                else -> c = dbrw.rawQuery("SELECT * FROM myTable WHERE book LIKE '" +
                        edBook.text.toString() + "'",null)
            }
            c.moveToFirst()
            items.clear()
            Toast.makeText(this,"共有"+c.count
                    +"筆資料", Toast.LENGTH_SHORT).show()
            for(i in 0 until c.count){
                items.add("書名:" + c.getString(0)+"\t\t\t\t 價格:" + c.getInt(1))
                c.moveToNext()
            }
            adapter.notifyDataSetChanged()
            c.close()
        }
        btnNew.setOnClickListener {
            if(edBook.length()<1 || edPrice.length()<1)
                Toast.makeText(this,"欄位請勿為空",
                    Toast.LENGTH_SHORT).show()
            else{
                try {
                    dbrw.execSQL("INSERT INTO myTable(book, price) VALUES(?,?)",
                         arrayOf(edBook.text.toString(),edPrice.text.toString()))

                    Toast.makeText(this, "新增書名" + edBook.text.toString()
                            + "    價格" + edPrice.text.toString(), Toast.LENGTH_SHORT).show()
                    edPrice.setText("")
                    edBook.setText("")
                }catch (e : Exception){
                    Toast.makeText(this,"新增失敗:"+
                            e.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
        btnUpdate.setOnClickListener {
            if(edBook.length()<1 || edPrice.length()<1)
                Toast.makeText(this,"欄位請勿為空",
                    Toast.LENGTH_SHORT).show()
            else{
                try {
                    dbrw.execSQL("UPDATE myTable SET price = "+
                            edPrice.text.toString() + " WHERE book LIKE '" +
                            edBook.text.toString() + "'")
                    Toast.makeText(this, "更新書名" + edBook.text.toString()
                            + "    價格" + edPrice.text.toString(), Toast.LENGTH_SHORT).show()
                    edPrice.setText("")
                    edBook.setText("")
                }catch (e : Exception){
                    Toast.makeText(this,"更新失敗:"+
                            e.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
        btnDel.setOnClickListener {
            if(edBook.length()<1)
                Toast.makeText(this,"書名請勿為空",
                    Toast.LENGTH_SHORT).show()
            else{
                try {
                    dbrw.execSQL("DELETE FROM myTable WHERE book LIKE '" +
                            edBook.text.toString() + "'")
                    Toast.makeText(this, "刪除書名" + edBook.text.toString()
                        ,Toast.LENGTH_SHORT).show()
                    edPrice.setText("")
                    edBook.setText("")
                }catch (e : Exception){
                    Toast.makeText(this,"刪除失敗:"+
                            e.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
    }

   /* override fun onDestroy() {
        super.onDestroy()
        dbrw.close()
    }*/
}
