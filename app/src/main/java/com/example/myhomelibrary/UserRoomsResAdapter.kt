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
class UserRoomsResAdapter(private val list: List<Room>, private val db: DatabaseHelper): RecyclerView.Adapter<UserRoomsResAdapter.UserRoomsResViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRoomsResAdapter.UserRoomsResViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_room_view,parent, false)
        return UserRoomsResViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: UserRoomsResAdapter.UserRoomsResViewHolder, position: Int) {
        holder.tvRoomId.text = list[position].id.toString()
        holder.tvRoomLabel.text = list[position].label
    }

    @SuppressLint("MissingInflatedId")
    inner class UserRoomsResViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvRoomId: TextView
        var tvRoomLabel: TextView

        init {
            tvRoomId = view.findViewById(R.id.tv_value_id)
            tvRoomLabel = view.findViewById(R.id.tv_value_label)

            itemView.setOnClickListener {
                val popupView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_room_rem, null)
                val dialog = AlertDialog.Builder(itemView.context)
                    .setView(popupView)
                    .create()

                val btnRemove = popupView.findViewById<Button>(R.id.btnRemove)

                // Handle button clicks
                btnRemove.setOnClickListener {
                    val sharedPreferences = itemView.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val userName = sharedPreferences.getString("userName", "") ?: ""

                    val databaseHelper = DatabaseHelper(itemView.context)
                    val rowsUpdated = databaseHelper.reserveRoomWithUser(userName, 0)

                    if (rowsUpdated > 0) {
                        Toast.makeText(itemView.context, "Room REMOVED successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(itemView.context, UserRoomsRes::class.java)
                        itemView.context.startActivity(intent)
                    } else {
                        Toast.makeText(itemView.context, "A Problem has occurred", Toast.LENGTH_SHORT).show()
                    }
                }



                dialog.show()
            }
        }

    }

}