package com.example.gram.screen.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.gram.R
import com.example.gram.data.models.Post
import com.example.gram.data.models.Story
import com.example.gram.data.repositories.PostsRepository
import com.example.gram.data.repositories.StoryRepository
import com.example.gram.ui.icon
import com.example.gram.util.PostView
import com.example.gram.util.StoryImage
import kotlinx.coroutines.launch


@Composable
fun Home() {

    val coroutineScope = rememberCoroutineScope()

    Scaffold(topBar = { Toolbar() }, bottomBar = {}) {
        val stories by StoryRepository.observeStories()
        val posts by PostsRepository.observePosts()

        ScrollableColumn {
            StoriesSection(stories = stories)
            Divider()

            PostList(posts = posts, onDoubleClick = { post ->
                coroutineScope.launch {
                    PostsRepository.performLike(postId = post.id)
                }
            }, onLikeToggle = { post ->
                coroutineScope.launch {
                    PostsRepository.toggleLike(postId = post.id)
                }
            })
        }
    }
}

@Composable
private fun Toolbar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalGravity = Alignment.CenterVertically
        ) {
            Icon(
                vectorResource(id = R.drawable.ic_camera),
                modifier = Modifier.icon()
            )
            Box(
                modifier = Modifier.padding(12.dp),
                gravity = ContentGravity.Center
            ) {
                Icon(vectorResource(id = R.drawable.ic_instagram))
            }
            Icon(imageResource(id = R.drawable.ic_dm), modifier = Modifier.icon())
        }
    }
}

@Composable
private fun StoriesSection(stories: List<Story>) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Stories", style = MaterialTheme.typography.subtitle2)
            Text(text = "Watch All", style = MaterialTheme.typography.subtitle2)
        }
        StoriesList(stories = stories)
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
private fun StoriesList(stories: List<Story>) {
    LazyRowFor(items = stories) { story ->
        Column(
            horizontalGravity = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
        ) {
            StoryImage(imageUrl = story.image)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = story.name, style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
private fun PostList(
    posts: List<Post>,
    onDoubleClick: (Post) -> Unit,
    onLikeToggle: (Post) -> Unit
) {
    posts.forEach { post ->
        PostView(post = post, onDoubleClick = onDoubleClick, onLikeToggle = onLikeToggle)
    }
}


