  package com.example.myhomelibrary


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myhomelibrary.databinding.ActivityLoginBinding

  class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var db:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DatabaseHelper(this)
        binding.textViewNewUser.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))

        }
        binding.buttonLogin.setOnClickListener {
            loginuser()
        }
    }

      private fun loginuser() {
          val name = binding.editTextLoginUsername.text.toString()
          val password = binding.editTextLoginPassword.text.toString()

          if (name.isEmpty() || password.isEmpty()){
              Toast.makeText(this, "Please do fill ann the information UwU", Toast.LENGTH_SHORT).show()
          }else{
                val exists = db.loginUser(name.trim(),password)
              if (exists){

                  Toast.makeText(this, "logged successfully", Toast.LENGTH_SHORT).show()
                        if (name == "othman"){
                            startActivity(Intent(this,AdminViewChoice::class.java))
                            finish()
                        }else{
                            val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("userName", name)
                            editor.apply()
                            startActivity(Intent(this,UserHome::class.java))

                        }

              }else {
                  Toast.makeText(this, "Wrong name or password", Toast.LENGTH_SHORT).show()
              }
              }
          }


      }
