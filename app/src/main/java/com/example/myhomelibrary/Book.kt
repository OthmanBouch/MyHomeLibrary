package com.example.myhomelibrary

data class Book(
    var id: Int = -1,
    var title: String = "",
    var author: String = "",
    var field: String = "",
    var status: String = "Available",
    var shelf: Int,
    var user: Int
)
