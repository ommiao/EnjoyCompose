package com.example.enjoycompose.ui.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.enjoycompose.WeViewModel
import com.example.enjoycompose.ui.theme.EnjoyComposeTheme
import kotlin.math.roundToInt

@Composable
fun ChatPage() {
    val viewModel: WeViewModel = viewModel()
    val currentChat = viewModel.currentChat
    val percentOffset = animateFloatAsState(if (viewModel.chatting) 0f else 1f)
    Column(
        modifier = Modifier
            .percentOffsetX(percentOffset.value)
            .background(EnjoyComposeTheme.colors.background)
            .fillMaxSize()
    ){
        if(currentChat != null){
            WeTopBar(title = currentChat.friend.name, onBack = {
                viewModel.endChat()
            })
        }
    }
}

fun Modifier.percentOffsetX(percent: Float): Modifier = this.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
        val offset = (percent * placeable.width).roundToInt()
        placeable.placeRelative(offset, 0)
    }
}