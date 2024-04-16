package com.example.myhomelibrary

data class Book_UserView(
    var id: Int = -1,
    var title: String = "",
    var author: String = "",
    var field: String = "",
    var status: String = "Available",
    var shelf: Int = 0,
)
