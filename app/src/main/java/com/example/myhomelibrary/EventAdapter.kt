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
class EventAdapter(private val list: List<Event>, private val db: DatabaseHelper): RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.EventViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_event_view, parent, false)
        return EventViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: EventAdapter.EventViewHolder, position: Int) {
        holder.tvEventId.text = list[position].id.toString()
        holder.tvEventName.text = list[position].name
        holder.tvEventGuest.text = list[position].guest.toString()
    }
    @SuppressLint("MissingInflatedId")
    inner class EventViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvEventId: TextView
        var tvEventName: TextView
        var tvEventGuest: TextView

        init {
            tvEventId = view.findViewById(R.id.tv_value_id)
            tvEventName = view.findViewById(R.id.tv_value_name)
            tvEventGuest = view.findViewById(R.id.tv_value_guest)

            itemView.setOnClickListener {
                val popupView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_events_ud, null)
                val dialog = AlertDialog.Builder(itemView.context)
                    .setView(popupView)
                    .create()

                val btnDelete = popupView.findViewById<Button>(R.id.btnDelete)
                val btnUpdate = popupView.findViewById<Button>(R.id.btnUpdate)

                // Handle button clicks
                btnDelete.setOnClickListener {
                    // Get the event ID of the clicked item
                    val eventIdToDelete = list[adapterPosition].id
                    // Delete the event record from the database using the ID
                    db.deleteEvent(eventIdToDelete)
                    // Remove the item from the list and notify adapter
                    list.toMutableList().removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)

                    dialog.dismiss()
                    val intent = Intent(itemView.context, SeeEvent::class.java)
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