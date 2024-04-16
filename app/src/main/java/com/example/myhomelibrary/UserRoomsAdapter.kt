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
class UserRoomsAdapter (private val list: List<Room>, private val db: DatabaseHelper): RecyclerView.Adapter<UserRoomsAdapter.UserRoomsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRoomsAdapter.UserRoomsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_room_view,parent, false)
        return UserRoomsViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: UserRoomsAdapter.UserRoomsViewHolder, position: Int) {
        holder.tvRoomId.text = list[position].id.toString()
        holder.tvRoomLabel.text = list[position].label
    }
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    inner class UserRoomsViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvRoomId: TextView
        var tvRoomLabel: TextView

        init {
            tvRoomId = view.findViewById(R.id.tv_value_id)
            tvRoomLabel = view.findViewById(R.id.tv_value_label)

            itemView.setOnClickListener {
                val popupView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_res_room, null)
                val dialog = AlertDialog.Builder(itemView.context)
                    .setView(popupView)
                    .create()

                val btnReserve = popupView.findViewById<Button>(R.id.btnReserve)

                // Handle button clicks
                btnReserve.setOnClickListener {
                    val sharedPreferences = itemView.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val userName = sharedPreferences.getString("userName", "") ?: ""

                    val roomIdToReserve = list[adapterPosition].id

                        if (roomIdToReserve > 0) {
                            val db = DatabaseHelper(itemView.context)
                            val updatedRows = db.reserveRoomWithUser(userName, roomIdToReserve)
                            db.close()

                            if (updatedRows > 0) {
                                Toast.makeText(itemView.context, "Room reserved successfully", Toast.LENGTH_SHORT).show()

                                val intent = Intent(itemView.context, UserRooms::class.java)
                                itemView.context.startActivity(intent)

                            } else {
                                Toast.makeText(itemView.context, "Failed to reserve room", Toast.LENGTH_SHORT).show()
                            }
                        }


                }



                dialog.show()
            }
        }

    }


}