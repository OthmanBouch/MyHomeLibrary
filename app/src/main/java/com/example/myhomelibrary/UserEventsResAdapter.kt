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
class UserEventsResAdapter(private val list: List<Event>, private val db: DatabaseHelper): RecyclerView.Adapter<UserEventsResAdapter.UserEventsResViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserEventsResAdapter.UserEventsResViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_event_view, parent, false)
        return UserEventsResViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: UserEventsResAdapter.UserEventsResViewHolder, position: Int) {
        holder.tvEventId.text = list[position].id.toString()
        holder.tvEventName.text = list[position].name
        holder.tvEventGuest.text = list[position].guest.toString()
    }
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    inner class UserEventsResViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvEventId: TextView
        var tvEventName: TextView
        var tvEventGuest: TextView

        init {
            tvEventId = view.findViewById(R.id.tv_value_id)
            tvEventName = view.findViewById(R.id.tv_value_name)
            tvEventGuest = view.findViewById(R.id.tv_value_guest)

            itemView.setOnClickListener {
                val popupView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_event_rem, null)
                val dialog = AlertDialog.Builder(itemView.context)
                    .setView(popupView)
                    .create()

                val btnRemove = popupView.findViewById<Button>(R.id.btnRemove)

                // Handle button clicks
                btnRemove.setOnClickListener {
                    val sharedPreferences = itemView.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val userName = sharedPreferences.getString("userName", "") ?: ""

                    val databaseHelper = DatabaseHelper(itemView.context)
                    val rowsUpdated = databaseHelper.reserveEventWithUser(userName, 0)

                    if (rowsUpdated > 0) {
                        Toast.makeText(itemView.context, "Event resmoved successfully successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(itemView.context, UserEvent::class.java)
                        itemView.context.startActivity(intent)
                    } else {
                        Toast.makeText(itemView.context, "Failed to remove event from reservation", Toast.LENGTH_SHORT).show()
                    }

                }



                dialog.show()
            }
        }
    }



}