package com.example.myhomelibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myhomelibrary.databinding.ActivityAddGuestBinding



class Add_Guest_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityAddGuestBinding
    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnADDSHELF.setOnClickListener {
            add_guest()
        }
        binding.textViewGoBack.setOnClickListener {
            startActivity(Intent(this,AdminViewChoice::class.java))
        }
    }

    private fun add_guest() {
        val name = binding.editTextName.text.toString()
        val job = binding.editTextJob.text.toString()

        if (name.isEmpty() || job.isEmpty()) {
            Toast.makeText(this, "Please fill in all the information", Toast.LENGTH_SHORT).show()
        } else {

                val guest = Guest(name = name, job = job)
                db.AddGuest(guest)
                Toast.makeText(this, "Guest record added successfully", Toast.LENGTH_SHORT).show()
            }
        }

}
