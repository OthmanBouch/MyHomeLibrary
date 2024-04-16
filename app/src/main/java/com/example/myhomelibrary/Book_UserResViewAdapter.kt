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
class Book_UserResViewAdapter(private val list: MutableList<Book_UserResView>, private val db: DatabaseHelper): RecyclerView.Adapter<Book_UserResViewAdapter.Book_UserResViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Book_UserResViewAdapter.Book_UserResViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user_see_res_book,parent, false)
        return Book_UserResViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: Book_UserResViewAdapter.Book_UserResViewHolder, position: Int) {
        holder.tvBookId.text = list[position].id.toString()
        holder.tvBookTitle.text = list[position].title
        holder.tvBookShelf.text = list[position].shelf.toString()
        holder.tvBookUser.text = list[position].user.toString()
    }

    @SuppressLint("MissingInflatedId")
    inner class Book_UserResViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var tvBookId: TextView
        var tvBookTitle: TextView
        var tvBookShelf: TextView
        var tvBookUser: TextView

        init {
            tvBookId = view.findViewById(R.id.tv_value_id)
            tvBookTitle = view.findViewById(R.id.tv_value_title)
            tvBookShelf = view.findViewById(R.id.tv_shelf_num)
            tvBookUser = view.findViewById(R.id.tv_user_id)

            itemView.setOnClickListener {
                val popupView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_book_rem, null)
                val dialog = AlertDialog.Builder(itemView.context)
                    .setView(popupView)
                    .create()

                val btnRemove = popupView.findViewById<Button>(R.id.btnRemove)

                // Handle button clicks
                btnRemove.setOnClickListener {
                    val bookId = list[adapterPosition].id
                    db.removeReservation(bookId) // Call the function to update status and user ID
                    list.removeAt(adapterPosition)
                    notifyDataSetChanged()
                    // Optionally, you can show a toast or perform other actions
                    dialog.dismiss()
                    Toast.makeText(itemView.context, "Reservation removed", Toast.LENGTH_SHORT).show()
                    val intent = Intent(itemView.context, UserHomeRes::class.java)
                    itemView.context.startActivity(intent)
                }

                dialog.show()
            }
        }
    }
}