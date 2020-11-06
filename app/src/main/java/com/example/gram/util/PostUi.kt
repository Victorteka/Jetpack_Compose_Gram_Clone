package com.example.gram.util

import android.text.format.DateUtils
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gram.R
import com.example.gram.data.models.Post
import com.example.gram.ui.defaultPadding
import com.example.gram.ui.horizontalPadding
import com.example.gram.ui.verticalPadding
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PostView(
    post: Post,
    onDoubleClick: (Post) -> Unit,
    onLikeToggle: (Post) -> Unit
) {
    val photoLikedAnimation = remember { mutableStateOf(false) }

    Column {
        PostHeader(post = post)
        Stack(
            modifier = Modifier.fillMaxWidth().height(300.dp).clickable(
                onClick = {},
                onDoubleClick = {
                    photoLikedAnimation.value = true
                    onDoubleClick.invoke(post)
                },
                indication = null
            ).background(color = Color.LightGray)
        ) {
            CoilImage(
                data = post.image,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            PhotoLikeAnimation(
                modifier = Modifier.fillMaxSize(),
                startAnimation = photoLikedAnimation.value,
                onAnimationComplete = {
                    photoLikedAnimation.value = false
                }
            )
        }
        PostFooter(post = post, onLikeToggle = onLikeToggle)
        Divider()
    }
}

@Composable
private fun PostHeader(post: Post) {
    Row(
        modifier = Modifier.fillMaxWidth().defaultPadding(),
        verticalGravity = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalGravity = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.preferredSize(30.dp)
                    .background(color = Color.LightGray, shape = CircleShape)
                    .clip(CircleShape)
            ) {
                CoilImage(
                    data = post.user.image,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = post.user.userName, style = MaterialTheme.typography.subtitle2)
            Icon(Icons.Filled.MoreVert)
        }
    }
}

@Composable
private fun PostFooter(
    post: Post,
    onLikeToggle: (Post) -> Unit
) {
    PostFooterIconSection(post = post, onLikeToggle = onLikeToggle)
    PostFooterTextSection(post = post)
}

@Composable
private fun PostFooterTextSection(post: Post) {
    Column(
        modifier = Modifier.padding(
            start = horizontalPadding,
            end = horizontalPadding,
            bottom = verticalPadding
        )
    ) {
        Text(text = "${post.likesCount} likes", style = MaterialTheme.typography.subtitle2)
        ProvideEmphasis(emphasis = EmphasisAmbient.current.medium) {
            Text(
                text = "View all ${post.commentsCount} comments",
                style = MaterialTheme.typography.caption
            )
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = post.timeStamp.getTimeElapsedText(),
                style = MaterialTheme.typography.caption.copy(fontSize = 10.sp)
            )
        }
    }
}

private fun Long.getTimeElapsedText(): String {
    val now = System.currentTimeMillis()
    val time = this

    return DateUtils.getRelativeTimeSpanString(
        time, now, 0L, DateUtils.FORMAT_ABBREV_TIME
    )
        .toString()
}

@Composable
private fun PostFooterIconSection(
    post: Post,
    onLikeToggle: (Post) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
        verticalGravity = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AnimLikeButton(post = post, onLikeClick = onLikeToggle)

        PostIconButton {
            Icon(imageResource(id = R.drawable.ic_outlined_comment))
        }

        PostIconButton {
            Icon(imageResource(id = R.drawable.ic_dm))
        }
    }
    PostIconButton {
        Icon(vectorResource(id = R.drawable.ic_outlined_bookmark))
    }
}

@Composable
fun PostIconButton(
    onClick: () -> Unit = { },
    icon: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(
                onClick = onClick,
                indication = RippleIndication(bounded = false, radius = 24.dp)
            )
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .then(Modifier.preferredSize(24.dp)),
        gravity = ContentGravity.Center,
        children = icon
    )
}