package com.example.myhomelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomelibrary.databinding.ActivitySeeBookBinding


class SeeBook : AppCompatActivity() {
    private lateinit var binding: ActivitySeeBookBinding
    private lateinit var db: DatabaseHelper
    private lateinit var recyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        val booklist = db.getAllBooksRecords().toMutableList()

        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(this@SeeBook)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = BookAdapter(booklist,db)

        }
    }
}