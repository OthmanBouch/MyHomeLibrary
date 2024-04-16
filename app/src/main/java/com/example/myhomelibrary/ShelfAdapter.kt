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

class ShelfAdapter(private val list: List<Shelf>, private val db: DatabaseHelper): RecyclerView.Adapter<ShelfAdapter.ShelfViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelfViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_shellf_view, parent, false)
        return ShelfViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ShelfAdapter.ShelfViewHolder, position: Int) {
        holder.tvShelfId.text = list[position].id.toString()
        holder.tvShelfLabel.text = list[position].label
        holder.tvShelfRoom.text = list[position].room.toString()
    }


    @SuppressLint("MissingInflatedId")
    inner class ShelfViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvShelfId: TextView
        var tvShelfLabel: TextView
        var tvShelfRoom: TextView

        init {
            tvShelfId = view.findViewById(R.id.tv_value_id)
            tvShelfLabel = view.findViewById(R.id.tv_value_label)
            tvShelfRoom = view.findViewById(R.id.tv_value_room)

            itemView.setOnClickListener {
                val popupView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_shelves_ud, null)
                val dialog = AlertDialog.Builder(itemView.context)
                    .setView(popupView)
                    .create()

                val btnDelete = popupView.findViewById<Button>(R.id.btnDelete)
                val btnUpdate = popupView.findViewById<Button>(R.id.btnUpdate)

                // Handle button clicks
                btnDelete.setOnClickListener {
                    // Get the shelf ID of the clicked item
                    val shelfIdToDelete = list[adapterPosition].id
                    // Delete the shelf record from the database using the ID
                    db.deleteShelf(shelfIdToDelete)
                    // Remove the item from the list and notify adapter
                    list.toMutableList().removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)

                    dialog.dismiss()
                    val intent = Intent(itemView.context, SeeShelves::class.java)
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