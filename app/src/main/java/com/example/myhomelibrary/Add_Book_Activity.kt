package com.example.myhomelibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myhomelibrary.databinding.ActivityAddBookBinding
import com.example.myhomelibrary.databinding.ActivityRegisterBinding

class Add_Book_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBookBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)


        binding.btnADDBOOK.setOnClickListener {
            add_book()
        }
        binding.textViewGoBack.setOnClickListener {
            startActivity(Intent(this,AdminViewChoice::class.java))
        }
    }

    private fun add_book() {
        val title = binding.editTextTitle.text.toString()
        val author = binding.editTextAuthor.text.toString()
        val field = binding.editTextField.text.toString()
        val status ="available"
        val shelf = 0
        val user = 0

        if (title.isEmpty() || author.isEmpty() || field.isEmpty()) {
            Toast.makeText(this, "Please fill in all information", Toast.LENGTH_SHORT).show()
        }else{
            val book = Book(title = title, author=author, field=field,status=status, shelf = shelf, user = user)
            db.AddBook(book)
            Toast.makeText(this, "Book added successfully", Toast.LENGTH_SHORT).show()
        }
    }
}