package com.example.myhomelibrary
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class RoomAdapter(private val list: List<Room>, private val db: DatabaseHelper): RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomAdapter.RoomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_room_view,parent, false)
        return RoomViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: RoomAdapter.RoomViewHolder, position: Int) {
        holder.tvRoomId.text = list[position].id.toString()
        holder.tvRoomLabel.text = list[position].label
    }
    @SuppressLint("MissingInflatedId")
    inner class RoomViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvRoomId: TextView
        var tvRoomLabel: TextView

        init {
            tvRoomId = view.findViewById(R.id.tv_value_id)
            tvRoomLabel = view.findViewById(R.id.tv_value_label)


            itemView.setOnClickListener {
                val popupView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_rooms_ud, null)
                val dialog = AlertDialog.Builder(itemView.context)
                    .setView(popupView)
                    .create()

                val btnDelete = popupView.findViewById<Button>(R.id.btnDelete)
                val btnUpdate = popupView.findViewById<Button>(R.id.btnUpdate)

                // Handle button clicks
                btnDelete.setOnClickListener {
                    // Get the room ID of the clicked item
                    val roomIdToDelete = list[adapterPosition].id
                    // Delete the room record from the database using the ID
                    db.deleteRoom(roomIdToDelete)
                    // Remove the item from the list and notify adapter
                    list.toMutableList().removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)

                    dialog.dismiss()
                    val intent = Intent(itemView.context, SeeRooms::class.java)
                    itemView.context.startActivity(intent)
                }

                btnUpdate.setOnClickListener {

                    dialog.dismiss()
                }

                dialog.show()
            }
        }

    }


}