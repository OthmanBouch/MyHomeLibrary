package com.example.myhomelibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myhomelibrary.databinding.ActivityAddEventBinding
import com.example.myhomelibrary.databinding.ActivityAddGuestBinding


class Add_Event_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEventBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnADDEVENT.setOnClickListener {
            add_event()
        }
        binding.textViewGoBack.setOnClickListener {
            startActivity(Intent(this,AdminViewChoice::class.java))
        }

    }

    private fun add_event() {
        val name = binding.editTextName.text.toString()
        val guest = binding.editTextGuest.text.toString()

        if (name.isEmpty() || guest.isEmpty()) {
            Toast.makeText(this, "Please fill in all the information", Toast.LENGTH_SHORT).show()
        } else {
            try {
                val guestNum = guest.toInt() // Try to convert the input to an integer
                val event = Event(name = name, guest = guestNum)
                db.AddEvent(event)
                Toast.makeText(this, "Event added successfully", Toast.LENGTH_SHORT).show()
            } catch (e: NumberFormatException) {
                // if above fails display error message uu
                Toast.makeText(this, "guest ID must be a valid integer", Toast.LENGTH_SHORT).show()
            }
        }
    }
}