package com.example.myhomelibrary

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val list: List<User>, private val db: DatabaseHelper) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user_view,parent, false)
        return UserViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.tvUserId.text = list[position].id.toString()
        holder.tvUserName.text = list[position].name
        holder.tvUserEmail.text = list[position].email
        holder.tvUserroom.text = list[position].room.toString()
        holder.tvUserevent.text = list[position].event.toString()

    }


    inner class UserViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvUserId: TextView
        var tvUserName: TextView
        var tvUserEmail: TextView
        var tvUserPassword: TextView
        var tvUserroom: TextView
        var tvUserevent: TextView

        init{
            tvUserId = view.findViewById(R.id.tv_value_id)
            tvUserName = view.findViewById(R.id.tv_value_name)
            tvUserEmail = view.findViewById(R.id.tv_value_email)
            tvUserPassword = view.findViewById(R.id.tv_value_password)
            tvUserroom = view.findViewById(R.id.tv_value_room)
            tvUserevent = view.findViewById(R.id.tv_value_event)

            itemView.setOnClickListener {
                val popupView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_layout, null)
                val dialog = AlertDialog.Builder(itemView.context)
                    .setView(popupView)
                    .create()

                val btnDelete = popupView.findViewById<Button>(R.id.btnDelete)
                val btnUpdate = popupView.findViewById<Button>(R.id.btnUpdate)

                // Handle button clicks
                btnDelete.setOnClickListener {
                    // Get the user ID of the clicked item
                    val userIdToDelete = list[adapterPosition].id
                    // Delete the user record from the database using the ID
                    db.deleteUser(userIdToDelete)
                    // Remove the item from the list and notify adapter
                    list.toMutableList().removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)

                    dialog.dismiss()
                    val intent = Intent(itemView.context, AdminHome::class.java)
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
