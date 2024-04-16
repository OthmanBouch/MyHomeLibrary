package com.example.myhomelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomelibrary.databinding.ActivitySeeRoomsBinding

class SeeRooms : AppCompatActivity() {
    private lateinit var binding: ActivitySeeRoomsBinding
    private lateinit var db: DatabaseHelper
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeRoomsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        val roomlist = db.getAllRoomsRecords().toMutableList()

        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(this@SeeRooms)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = RoomAdapter(roomlist,db)

        }
    }
}