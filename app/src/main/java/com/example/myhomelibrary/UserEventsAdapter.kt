package com.example.myhomelibrary

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class UserEventsAdapter(private val list: List<Event>, private val db: DatabaseHelper): RecyclerView.Adapter<UserEventsAdapter.UserEventsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserEventsAdapter.UserEventsViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_event_view, parent, false)
        return UserEventsViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: UserEventsAdapter.UserEventsViewHolder, position: Int) {
        holder.tvEventId.text = list[position].id.toString()
        holder.tvEventName.text = list[position].name
        holder.tvEventGuest.text = list[position].guest.toString()
    }

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    inner class UserEventsViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvEventId: TextView
        var tvEventName: TextView
        var tvEventGuest: TextView

        init {
            tvEventId = view.findViewById(R.id.tv_value_id)
            tvEventName = view.findViewById(R.id.tv_value_name)
            tvEventGuest = view.findViewById(R.id.tv_value_guest)

            itemView.setOnClickListener {
                val popupView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_res_event, null)
                val dialog = AlertDialog.Builder(itemView.context)
                    .setView(popupView)
                    .create()

                val btnReserve = popupView.findViewById<Button>(R.id.btnReserve)

                // Handle button clicks
                btnReserve.setOnClickListener {
                    val sharedPreferences = itemView.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val userName = sharedPreferences.getString("userName", "") ?: ""

                    val eventIdToReserve =  list[adapterPosition].id

                        if (eventIdToReserve > 0) {
                            val db = DatabaseHelper(itemView.context)
                            val updatedRows = db.reserveEventWithUser(userName, eventIdToReserve)
                            db.close()

                            if (updatedRows > 0) {
                                Toast.makeText(itemView.context, "Event reserved successfully", Toast.LENGTH_SHORT).show()
                                val intent = Intent(itemView.context, UserEvent::class.java)
                                itemView.context.startActivity(intent)
                            } else {
                                Toast.makeText(itemView.context, "Failed to reserve event", Toast.LENGTH_SHORT).show()
                            }
                        }

                }



                dialog.show()
            }
        }
    }
}