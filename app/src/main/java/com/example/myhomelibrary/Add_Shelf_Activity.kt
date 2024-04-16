package com.example.myhomelibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myhomelibrary.databinding.ActivityAddShelfBinding

class Add_Shelf_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityAddShelfBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddShelfBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnADDSHELF.setOnClickListener {
            add_shelf()
        }
        binding.textViewGoBack.setOnClickListener {
            startActivity(Intent(this,AdminViewChoice::class.java))
        }
    }
    private fun add_shelf() {
        val label = binding.editTextLabel.text.toString()
        val room = binding.editTextRoomNum.text.toString()

        if (label.isEmpty() || room.isEmpty()) {
            Toast.makeText(this, "Please fill in all the information", Toast.LENGTH_SHORT).show()
        } else {
            try {
                val roomNum = room.toInt() // Try to convert the input to an integer
                val shelf = Shelf(label = label, room = roomNum)
                db.AddShelf(shelf)
                Toast.makeText(this, "Shelf added successfully", Toast.LENGTH_SHORT).show()
            } catch (e: NumberFormatException) {
                // if above fails display error message uu
                Toast.makeText(this, "Room number must be a valid integer", Toast.LENGTH_SHORT).show()
            }
        }
    }

}