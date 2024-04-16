package com.example.myhomelibrary

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import com.example.myhomelibrary.databinding.ActivityAdminViewChoiceBinding
import com.example.myhomelibrary.databinding.ActivityLoginBinding

class AdminViewChoice : AppCompatActivity() {
    private lateinit var binding:ActivityAdminViewChoiceBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminViewChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ViewUsers.setOnClickListener {
            startActivity(Intent(this,AdminHome::class.java))
        }


        binding.ViewBooks.setOnClickListener {
            val popupView = LayoutInflater.from(this).inflate(R.layout.popup_books_layout, null)
            val dialog = AlertDialog.Builder(this)
                .setView(popupView)
                .create()

            val btnADD_BOOK = popupView.findViewById<Button>(R.id.btnADD_BOOK)
            val btnSEE_BOOK = popupView.findViewById<Button>(R.id.btnSEE_BOOK)


            btnADD_BOOK.setOnClickListener {
                val intent = Intent(this, Add_Book_Activity::class.java) // Replace with your desired activity
                startActivity(intent)
                dialog.dismiss()
            }

            btnSEE_BOOK.setOnClickListener {
                val intent = Intent(this, SeeBook::class.java) // Replace with your desired activity
                startActivity(intent)
                dialog.dismiss()
            }

            dialog.show()
        }


        binding.ViewRooms.setOnClickListener {
            val popupView = LayoutInflater.from(this).inflate(R.layout.popup_rooms_layout, null)
            val dialog = AlertDialog.Builder(this)
                .setView(popupView)
                .create()

            val btnADD_ROOM = popupView.findViewById<Button>(R.id.btnADD_ROOM)
            val btnSEE_ROOM = popupView.findViewById<Button>(R.id.btnSEE_ROOM)


            btnADD_ROOM.setOnClickListener {
                val intent = Intent(this, Add_Room_Activity::class.java) // Replace with your desired activity
                startActivity(intent)
                dialog.dismiss()
            }

            btnSEE_ROOM.setOnClickListener {
                val intent = Intent(this, SeeRooms::class.java) // Replace with your desired activity
                startActivity(intent)
                dialog.dismiss()
            }

            dialog.show()
        }


        binding.ViewShelves.setOnClickListener {
            val popupView = LayoutInflater.from(this).inflate(R.layout.popup_shelves_layout, null)
            val dialog = AlertDialog.Builder(this)
                .setView(popupView)
                .create()

            val btnADD_SHELF = popupView.findViewById<Button>(R.id.btnADD_SHELF)
            val btnSEE_SHELF = popupView.findViewById<Button>(R.id.btnSEE_SHELF)


            btnADD_SHELF.setOnClickListener {
                val intent = Intent(this, Add_Shelf_Activity::class.java) // Replace with your desired activity
                startActivity(intent)
                dialog.dismiss()
            }

            btnSEE_SHELF.setOnClickListener {
                val intent = Intent(this, SeeShelves::class.java) // Replace with your desired activity
                startActivity(intent)
                dialog.dismiss()
            }

            dialog.show()
        }
        binding.ViewGuests.setOnClickListener {
            val popupView = LayoutInflater.from(this).inflate(R.layout.popup_guests_layout, null)
            val dialog = AlertDialog.Builder(this)
                .setView(popupView)
                .create()

            val btnADD_GUEST = popupView.findViewById<Button>(R.id.btnADD_GUEST)
            val btnSEE_GUEST = popupView.findViewById<Button>(R.id.btnSEE_GUEST)


            btnADD_GUEST.setOnClickListener {
                val intent = Intent(this, Add_Guest_Activity::class.java) // Replace with your desired activity
                startActivity(intent)
                dialog.dismiss()
            }

            btnSEE_GUEST.setOnClickListener {
                val intent = Intent(this, SeeGuests::class.java) // Replace with your desired activity
                startActivity(intent)
                dialog.dismiss()
            }

            dialog.show()
        }
        binding.ViewEvents.setOnClickListener {
            val popupView = LayoutInflater.from(this).inflate(R.layout.popup_events_layout, null)
            val dialog = AlertDialog.Builder(this)
                .setView(popupView)
                .create()

            val btnADD_EVENT = popupView.findViewById<Button>(R.id.btnADD_EVENT)
            val btnSEE_EVENT = popupView.findViewById<Button>(R.id.btnSEE_EVENT)


            btnADD_EVENT.setOnClickListener {
                    val intent = Intent(this, Add_Event_Activity::class.java) // Replace with your desired activity
                startActivity(intent)
                dialog.dismiss()
            }

            btnSEE_EVENT.setOnClickListener {
                val intent = Intent(this, SeeEvent::class.java) // Replace with your desired activity
                startActivity(intent)
                dialog.dismiss()
            }

            dialog.show()
        }
        binding.LogOut.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        binding.AboutMHL.setOnClickListener {
            startActivity(Intent(this,AdminViewChoice::class.java))
        }
    }
}