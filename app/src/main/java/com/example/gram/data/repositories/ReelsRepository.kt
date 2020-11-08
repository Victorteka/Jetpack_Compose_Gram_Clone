package com.example.gram.data.repositories

import com.example.gram.data.models.Reel
import com.example.gram.data.models.User
import com.example.gram.data.models.names
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
object ReelsRepository {
    private val reels = arrayListOf<Reel>()

    init {
        populateReels()
    }

    private fun populateReels() {
        val _reels = ArrayList<Reel>()

        (0..9).forEach { index ->
            val post = Reel(
                id = index + 1,
                video = videos[index],
                user = User(
                    name = names[index],
                    userName = names[index],
                    image = "https://randomuser.me/api/portraits/men/${index + 1}.jpg"
                ),
                likesCount = index + 100,
                commentsCount = index + 20
            )
            _reels.add(post)
        }
        reels.clear()
        reels.addAll(_reels)
    }

    fun getReels(): List<Reel> = reels
}

private val videos = listOf(
    "food.mp4",
    "soap-bubbles.mp4",
    "castle.mp4",
    "lake.mp4",
    "icecream.mp4",
    "soap-bubbles.mp4",
    "castle.mp4",
    "lake.mp4",
    "icecream.mp4",
    "soap-bubbles.mp4",
    "castle.mp4",
    "lake.mp4",
    "icecream.mp4",
)