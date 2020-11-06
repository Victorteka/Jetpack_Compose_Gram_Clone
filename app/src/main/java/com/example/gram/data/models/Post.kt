package com.example.gram.data.models

data class Post(
    val id: Int,
    val image: String,
    val user: User,
    val isLiked: Boolean = false,
    val likesCount: Int,
    val commentsCount: Int,
    val timeStamp: Long
)