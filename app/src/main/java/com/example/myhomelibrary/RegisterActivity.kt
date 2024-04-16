package com.example.myhomelibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myhomelibrary.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)
        binding.buttonRegister.setOnClickListener {
            registerUser()
        }







        binding.textViewExistingUser.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))

        }
    }

    private fun registerUser() {
        val name = binding.editTextRegUsername.text.toString()
        val email = binding.editTextRegEmail.text.toString()
        val password = binding.editTextRegPassword.text.toString()
        val cpass = binding.editTextRegConfirmPassword.text.toString()

        if (name.isEmpty() || email .isEmpty() || password.isEmpty() || cpass.isEmpty()){
            Toast.makeText(this, "Please fill in all information UwU", Toast.LENGTH_SHORT).show()
        }else{if (password.compareTo(cpass) == 0){
                val user = User(name = name, email = email, password = password)
                db.registerUser(user)
            Toast.makeText(this, "Account added successfully", Toast.LENGTH_SHORT).show()
                }else{
            Toast.makeText(this, "Passwords not matching sussy baka!!", Toast.LENGTH_SHORT).show()
                }

        }
    }
}