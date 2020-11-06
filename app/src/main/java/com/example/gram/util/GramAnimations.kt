package com.example.gram.util

import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.transition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.gram.R
import com.example.gram.data.models.Post


internal enum class AnimationState {
    START,
    MID,
    END
}

//dp Animation
private val dpPropKey = DpPropKey()
private val dpAnimDefinition = transitionDefinition<Int> {
    state(AnimationState.START.ordinal) { this[dpPropKey] = 0.dp }
    state(AnimationState.END.ordinal) { this[dpPropKey] = 100.dp }

    transition(0 to 2, 2 to 0){
        dpPropKey using spring(
            stiffness = Spring.StiffnessMedium
        )
    }
}

@Composable
fun PhotoLikeAnimation(
    modifier: Modifier,
    startAnimation: Boolean,
    onAnimationComplete:() -> Unit
){
    var dpStartState = remember { mutableStateOf(AnimationState.START.ordinal) }
    var dpEndState = remember { mutableStateOf(AnimationState.START.ordinal) }
    var isAnimating = remember { mutableStateOf(false) }

    val dpAnim = transition(
        definition = dpAnimDefinition,
        initState = dpStartState.value,
        toState = dpEndState.value,
        onStateChangeFinished = {
            when (it) {
                AnimationState.END.ordinal -> {
                    dpStartState.value = AnimationState.END.ordinal
                    dpEndState.value = AnimationState.START.ordinal

                    isAnimating.value = false
                    onAnimationComplete.invoke()
                }
            }
        }
    )

    Stack(modifier = modifier) {
        Icon(
            asset = imageResource(id = R.drawable.ic_filled_favorite),
            tint = Color.White,
            modifier = Modifier.preferredSize(dpAnim[dpPropKey])
                .gravity(Alignment.Center)
        )
    }

    if (startAnimation && !isAnimating.value) {
        dpEndState.value = AnimationState.END.ordinal
        isAnimating.value = true
    }

}

@Composable
fun AnimLikeButton(
    post: Post,
    onLikeClick: (Post) -> Unit
) {
    val contentColor = contentColor()
    var dpStartState = remember { mutableStateOf(AnimationState.START.ordinal) }
    var dpEndState = remember { mutableStateOf(AnimationState.START.ordinal) }
    var isAnimating = remember { mutableStateOf(false) }
    var likeIconRes = remember { mutableStateOf(R.drawable.ic_outlined_favorite) }
    var likeIconColor = remember { mutableStateOf(contentColor) }

    val dpAnim = transition(
        definition = dpAnimDefinition,
        initState = dpStartState.value,
        toState = dpEndState.value,
        onStateChangeFinished = {
            when (it) {
                AnimationState.MID.ordinal -> {
                    dpStartState.value = AnimationState.MID.ordinal
                    dpEndState.value = AnimationState.END.ordinal
                }
                AnimationState.END.ordinal -> {
                    dpStartState.value = AnimationState.END.ordinal
                    dpEndState.value = AnimationState.START.ordinal

                    likeIconRes.value = if (post.isLiked) {
                        R.drawable.ic_filled_favorite
                    } else {
                        R.drawable.ic_outlined_favorite
                    }

                    likeIconColor.value = if (post.isLiked) {
                        Color.Red
                    } else {
                        contentColor
                    }

                    isAnimating.value = false

                }
            }
        }
    )

    if (!isAnimating.value) {

        likeIconRes.value = if (post.isLiked) {
            R.drawable.ic_filled_favorite
        } else {
            R.drawable.ic_outlined_favorite
        }

        likeIconColor.value = if (post.isLiked) {
            Color.Red
        } else {
            contentColor
        }
    }

    Box(
        modifier = Modifier
            .clickable(
                onClick = {
                    dpEndState.value = AnimationState.MID.ordinal
                    isAnimating.value = true
                    onLikeClick.invoke(post)
                },
                indication = RippleIndication(bounded = false, radius = 24.dp)
            )
            .padding(vertical = 10.dp)
            .then(Modifier.preferredSize(30.dp)),
        gravity = ContentGravity.Center
    ) {
        Icon(
            asset = imageResource(id = likeIconRes.value), tint = likeIconColor.value,
            modifier = Modifier.preferredSize(dpAnim[dpPropKey])
        )
    }

}