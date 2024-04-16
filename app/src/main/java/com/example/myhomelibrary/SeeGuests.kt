package com.example.myhomelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomelibrary.databinding.ActivitySeeGuestsBinding


class SeeGuests : AppCompatActivity() {
    private lateinit var binding: ActivitySeeGuestsBinding
    private lateinit var db: DatabaseHelper
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeGuestsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        val guestlist = db.getAllGuestsRecords().toMutableList()

        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(this@SeeGuests)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = GuestAdapter(guestlist,db)

        }
    }
}