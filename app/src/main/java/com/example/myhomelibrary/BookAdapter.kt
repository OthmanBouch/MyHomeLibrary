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
class BookAdapter(private val list: List<Book>, private val db: DatabaseHelper): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_book_view,parent, false)
        return BookViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.tvBookId.text = list[position].id.toString()
        holder.tvBookTitle.text = list[position].title
        holder.tvBookAuthor.text = list[position].author
        holder.tvBookField.text = list[position].field
        holder.tvBookStatus.text = list[position].status
        holder.tvBookShelf.text = list[position].shelf.toString()
        holder.tvBookUser.text = list[position].user.toString()
    }
    @SuppressLint("MissingInflatedId")
    inner class BookViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvBookId: TextView
        var tvBookTitle: TextView
        var tvBookAuthor: TextView
        var tvBookField: TextView
        var tvBookStatus: TextView
        var tvBookShelf: TextView
        var tvBookUser: TextView
        init {
            tvBookId = view.findViewById(R.id.tv_value_id)
            tvBookTitle = view.findViewById(R.id.tv_value_title)
            tvBookAuthor = view.findViewById(R.id.tv_value_author)
            tvBookField = view.findViewById(R.id.tv_value_field)
            tvBookStatus = view.findViewById(R.id.tv_value_status)
            tvBookShelf = view.findViewById(R.id.tv_shelf_num)
            tvBookUser = view.findViewById(R.id.tv_user_id)

            itemView.setOnClickListener {
                val popupView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_books_ud, null)
                val dialog = AlertDialog.Builder(itemView.context)
                    .setView(popupView)
                    .create()

                val btnDelete = popupView.findViewById<Button>(R.id.btnDelete)
                val btnUpdate = popupView.findViewById<Button>(R.id.btnUpdate)

                // Handle button clicks
                btnDelete.setOnClickListener {
                    // Get the book ID of the clicked item
                    val bookIdToDelete = list[adapterPosition].id
                    // Delete the book record from the database using the ID
                    db.deleteBook(bookIdToDelete)
                    // Remove the item from the list and notify adapter
                    list.toMutableList().removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)

                    dialog.dismiss()
                    val intent = Intent(itemView.context, SeeBook::class.java)
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