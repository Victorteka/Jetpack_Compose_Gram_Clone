package com.example.gram.data.models


data class User(
    val name: String,
    val userName: String,
    val image: String
)

val currentUser = User(
    name = "Victor Teka",
    userName = "Vicky_teka",
    image = "https://s.gravatar.com/avatar/270261ab18032094e6a69d9b7ee40a1a?s=80"
)