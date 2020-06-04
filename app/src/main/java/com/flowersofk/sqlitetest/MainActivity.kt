package com.flowersofk.sqlitetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var helper : RoomHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_memo")
            .allowMainThreadQueries()
            .build()

        val adapter = RecyclerAdapter()
        adapter.listData = helper?.RoomMemoDao()?.getAll() ?: mutableListOf()
        adapter.helper = helper

        recyclerMemo.adapter = adapter
        recyclerMemo.layoutManager = LinearLayoutManager(this)


        buttonSave.setOnClickListener {
            if(editMemo.text.toString().isNotEmpty()) {

                val memo = RoomMemo(editMemo.text.toString(), System.currentTimeMillis())

//                helper.insertMemo(memo)
                helper?.RoomMemoDao()?.insert(memo)

                adapter.listData.clear()
                adapter.listData.addAll(helper?.RoomMemoDao()?.getAll() ?: mutableListOf())
                adapter.notifyDataSetChanged()

                editMemo.setText("")

            }
        }




    }
}
