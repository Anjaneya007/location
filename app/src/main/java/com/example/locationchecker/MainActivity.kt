package com.example.locationchecker

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.locationchecker.room.Item
import com.example.locationchecker.room.ItemDatabase

class MainActivity : AppCompatActivity() {
    lateinit var recycleview: RecyclerView
    lateinit var db: RoomDatabase
    lateinit var requiredList: ArrayList<Item>
    lateinit var adapter: ItemAdapter
    lateinit var items: List<Item>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleview=findViewById(R.id.recyclerview)

        supportActionBar?.title = "Location Checker"

        val actionBar: ActionBar?
        actionBar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#800080"))
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(colorDrawable)
        }

        db = Room.databaseBuilder(
            applicationContext, ItemDatabase::class.java, "items"
        ).allowMainThreadQueries().build()

        val meDao = (db as ItemDatabase).itemDao()

        //populate data here
        val a=Item(0,"Abhi",17.5,78.5)
        val b=Item(0,"Prashant",16.0,78.0)
        val c=Item(0,"Vamsi",18.0,77.0)
        val d=Item(0,"Jaswanth",17.4343648,78.3955874)
        val e=Item(0,"Sai",17.0,78.0)
//
        meDao.insertItem(a)
        meDao.insertItem(b)
        meDao.insertItem(c)
        meDao.insertItem(d)
        meDao.insertItem(e)




        items= meDao.getAll()
        requiredList = items as ArrayList<Item>

        //adapter
        adapter = ItemAdapter(this, requiredList)
        recycleview.layoutManager = LinearLayoutManager(this)
        recycleview.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        requiredList.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        requiredList.clear()

    }
}