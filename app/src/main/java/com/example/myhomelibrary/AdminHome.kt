package com.example.myhomelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomelibrary.databinding.ActivityAdminHomeBinding

class AdminHome : AppCompatActivity() {
    private lateinit var binding: ActivityAdminHomeBinding
    private lateinit var db: DatabaseHelper
    private lateinit var recyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        val userlist = db.getAllUsersAccount().toMutableList()

        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(this@AdminHome)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = UserAdapter(userlist,db)

        }

    }



}

