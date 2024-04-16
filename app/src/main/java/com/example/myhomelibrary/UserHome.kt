package com.example.myhomelibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomelibrary.databinding.ActivityUserHomeBinding

class UserHome : AppCompatActivity() {
    private lateinit var binding: ActivityUserHomeBinding
    private lateinit var db: DatabaseHelper
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        val bookUserviewlist = db.getAllBooksUserViewRecords().toMutableList()

        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(this@UserHome)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = Book_UserViewAdapter(bookUserviewlist,db)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ViewBooks -> startActivity(Intent(this,UserHome::class.java))
            R.id.ReservedBooks -> startActivity(Intent(this,UserHomeRes::class.java))
            R.id.ViewRooms -> startActivity(Intent(this,UserRooms::class.java))
            R.id.ViewEvents -> startActivity(Intent(this,UserEvent::class.java))
            R.id.ViewSettings -> startActivity(Intent(this,LoginActivity::class.java))
        }
        return true
    }
}