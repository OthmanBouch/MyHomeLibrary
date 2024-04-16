package com.example.myhomelibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomelibrary.databinding.ActivitySeeRoomsBinding
import com.example.myhomelibrary.databinding.ActivityUserRoomsBinding

class UserRooms : AppCompatActivity() {
    private lateinit var binding: ActivitySeeRoomsBinding
    private lateinit var db: DatabaseHelper
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeRoomsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        val roomlist = db.getAllRoomsRecords().toMutableList()

        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(this@UserRooms)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = UserRoomsAdapter(roomlist,db)

        }
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_barroom_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ViewBooks -> startActivity(Intent(this,UserHome::class.java))
            R.id.ReservedRoom -> startActivity(Intent(this,UserRoomsRes::class.java))
            R.id.ViewRooms -> startActivity(Intent(this,UserRooms::class.java))
            R.id.ViewEvents -> startActivity(Intent(this,UserEvent::class.java))
            R.id.LogOut -> startActivity(Intent(this,LoginActivity::class.java))
            R.id.ViewSettings -> startActivity(Intent(this,UserHome::class.java))
        }
        return true
    }
}