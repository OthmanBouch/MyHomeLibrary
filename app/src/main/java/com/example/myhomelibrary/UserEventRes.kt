package com.example.myhomelibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomelibrary.databinding.ActivitySeeEventBinding

class UserEventRes : AppCompatActivity() {
    private lateinit var binding: ActivitySeeEventBinding
    private lateinit var db: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DatabaseHelper(this)

        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val username = sharedPreferences.getString("userName", "")

        val eventlist = username?.let { db.getReservedEventsForUser(it).toMutableList() }
        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(this@UserEventRes)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = eventlist?.let { UserEventsResAdapter(it,db) }

        }
    }







    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_barevent_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ViewBooks -> startActivity(Intent(this,UserHome::class.java))
            R.id.ReservedEvent -> startActivity(Intent(this,UserEventRes::class.java))
            R.id.ViewRooms -> startActivity(Intent(this,UserRooms::class.java))
            R.id.ViewEvents -> startActivity(Intent(this,UserEvent::class.java))
            R.id.LogOut -> startActivity(Intent(this,LoginActivity::class.java))
            R.id.ViewSettings -> startActivity(Intent(this,UserHome::class.java))
        }
        return true
    }

}