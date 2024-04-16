package com.example.myhomelibrary
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.myhomelibrary.databinding.ActivitySeeShelvesBinding

class SeeShelves : AppCompatActivity() {
    private lateinit var binding: ActivitySeeShelvesBinding
    private lateinit var db: DatabaseHelper
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeShelvesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = DatabaseHelper(this)

        val shelflist = db.getAllShelvesRecords().toMutableList()

        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(this@SeeShelves)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = ShelfAdapter(shelflist,db)

        }
    }
}