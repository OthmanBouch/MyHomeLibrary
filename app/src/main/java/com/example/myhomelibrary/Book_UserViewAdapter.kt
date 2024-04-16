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
class Book_UserViewAdapter(private val list: MutableList<Book_UserView>, private val db: DatabaseHelper): RecyclerView.Adapter<Book_UserViewAdapter.Book_UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Book_UserViewAdapter.Book_UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user_see_book,parent, false)
        return Book_UserViewHolder(itemView)

    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: Book_UserViewAdapter.Book_UserViewHolder, position: Int) {
        holder.tvBookId.text = list[position].id.toString()
        holder.tvBookTitle.text = list[position].title
        holder.tvBookAuthor.text = list[position].author
        holder.tvBookField.text = list[position].field
        holder.tvBookStatus.text = list[position].status
        holder.tvBookShelf.text = list[position].shelf.toString()
    }
    @SuppressLint("MissingInflatedId")
    inner class Book_UserViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var tvBookId: TextView
        var tvBookTitle: TextView
        var tvBookAuthor: TextView
        var tvBookField: TextView
        var tvBookStatus: TextView
        var tvBookShelf: TextView


        init {
            tvBookId = view.findViewById(R.id.tv_value_id)
            tvBookTitle = view.findViewById(R.id.tv_value_title)
            tvBookAuthor = view.findViewById(R.id.tv_value_author)
            tvBookField = view.findViewById(R.id.tv_value_field)
            tvBookStatus = view.findViewById(R.id.tv_value_status)
            tvBookShelf = view.findViewById(R.id.tv_shelf_num)


            itemView.setOnClickListener {
                val popupView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_res_book, null)
                val dialog = AlertDialog.Builder(itemView.context)
                    .setView(popupView)
                    .create()

                val btnReserve = popupView.findViewById<Button>(R.id.btnReserve)

                // Handle button clicks
                btnReserve.setOnClickListener {
                    val sharedPreferences = itemView.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val userName = sharedPreferences.getString("userName", "") ?: ""

                    if (userName.isNotEmpty()) {
                        val bookId = list[adapterPosition].id

                        val updatedRows = db.reserveBookAndUpdateStatus(bookId, userName)

                        if (updatedRows > 0) {
                            list[adapterPosition].status = "Not Available"
                            notifyDataSetChanged()
                            dialog.dismiss()
                            Toast.makeText(itemView.context, "Book reserved successfully", Toast.LENGTH_SHORT).show()

                            val intent = Intent(itemView.context, UserHome::class.java)
                            itemView.context.startActivity(intent)
                        } else {
                            Toast.makeText(itemView.context, "Failed to reserve the book", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(itemView.context, "Username not available", Toast.LENGTH_SHORT).show()
                    }
                }



                dialog.show()
            }
        }
    }}