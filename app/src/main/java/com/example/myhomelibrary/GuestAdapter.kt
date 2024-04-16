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
class GuestAdapter(private val list: List<Guest>, private val db: DatabaseHelper): RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestAdapter.GuestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_guest_view, parent, false)
        return GuestViewHolder(itemView)

    }


    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: GuestAdapter.GuestViewHolder, position: Int) {
        holder.tvGuestId.text = list[position].id.toString()
        holder.tvGuestName.text = list[position].name
        holder.tvGuestJob.text = list[position].job
    }

    @SuppressLint("MissingInflatedId")
    inner class GuestViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvGuestId: TextView
        var tvGuestName: TextView
        var tvGuestJob: TextView

        init {
            tvGuestId = view.findViewById(R.id.tv_value_id)
            tvGuestName = view.findViewById(R.id.tv_value_name)
            tvGuestJob = view.findViewById(R.id.tv_value_job)

            itemView.setOnClickListener {
                val popupView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_guests_ud, null)
                val dialog = AlertDialog.Builder(itemView.context)
                    .setView(popupView)
                    .create()

                val btnDelete = popupView.findViewById<Button>(R.id.btnDelete)
                val btnUpdate = popupView.findViewById<Button>(R.id.btnUpdate)

                // Handle button clicks
                btnDelete.setOnClickListener {
                    // Get the guest ID of the clicked item
                    val guestIdToDelete = list[adapterPosition].id
                    // Delete the guest record from the database using the ID
                    db.deleteGuest(guestIdToDelete)
                    // Remove the item from the list and notify adapter
                    list.toMutableList().removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)

                    dialog.dismiss()
                    val intent = Intent(itemView.context, SeeGuests::class.java)
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
