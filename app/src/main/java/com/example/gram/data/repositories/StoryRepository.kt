package com.example.gram.data.repositories

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.gram.data.models.Story
import com.example.gram.data.models.currentUser
import com.example.gram.data.models.names
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
object StoryRepository {
    private val stories = mutableStateOf<List<Story>>(emptyList())

    init {
        populateStories()
    }

    private fun populateStories(){
        val _stories = ArrayList<Story>()

        _stories.add(
            Story(
                image = currentUser.image,
                name = "Your story"
            )
        )
        (0..9).forEach { index ->
            val story = Story(
                image = "https://randomuser.me/api/portraits/men/${index + 1}.jpg",
                name = names[index]
            )
            _stories.add(story)
        }
        stories.value = _stories
    }

    fun observeStories(): MutableState<List<Story>> = stories
}