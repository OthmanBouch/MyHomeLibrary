package com.example.myhomelibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myhomelibrary.databinding.ActivityAddBookBinding
import com.example.myhomelibrary.databinding.ActivityAddRoomBinding

class Add_Room_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRoomBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnADDROOM.setOnClickListener {
            add_room()
        }
        binding.textViewGoBack.setOnClickListener {
            startActivity(Intent(this,AdminViewChoice::class.java))
        }
    }
    private fun add_room() {
        val label = binding.editTextLabel.text.toString()

        if (label.isEmpty()) {
            Toast.makeText(this, "Please fill in all information", Toast.LENGTH_SHORT).show()
        }else{
            val room = Room(label = label)
            db.AddRoom(room)
            Toast.makeText(this, "Room added successfully", Toast.LENGTH_SHORT).show()
        }
    }
}