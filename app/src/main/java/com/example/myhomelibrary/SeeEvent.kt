package com.example.myhomelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomelibrary.databinding.ActivitySeeEventBinding


class SeeEvent : AppCompatActivity() {
    private lateinit var binding: ActivitySeeEventBinding
    private lateinit var db: DatabaseHelper
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        val eventlist = db.getAllEventsRecords().toMutableList()

        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(this@SeeEvent)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = EventAdapter(eventlist,db)

        }
    }
}