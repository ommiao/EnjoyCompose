package com.example.enjoycompose.ui.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.enjoycompose.R
import com.example.enjoycompose.WeViewModel
import com.example.enjoycompose.ui.theme.EnjoyComposeTheme
import com.rengwuxian.wecompose.data.Msg
import com.rengwuxian.wecompose.data.User
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
    ) {
        if (currentChat != null) {
            WeTopBar(title = currentChat.friend.name, onBack = {
                viewModel.endChat()
            })
            LazyColumn(Modifier.weight(1f)) {
                items(currentChat.msgs) { msg ->
                    ChatMsgItem(msg)
                }
            }
            ChatBottomBar()
        }
    }
}

@Preview
@Composable
fun ChatBottomBar() {
    Row(
        Modifier.background(color = EnjoyComposeTheme.colors.bottomBar).padding(10.dp)
    ) {
        Icon(painterResource(R.drawable.ic_voice), "voice",
            Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically),
            tint = EnjoyComposeTheme.colors.icon
        )
        Spacer(Modifier
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = EnjoyComposeTheme.colors.textFieldBackground)
                .weight(1f)
                .height(32.dp)
                .align(Alignment.CenterVertically)
        )
        Text(text = "\uD83D\uDCA3", fontSize = 28.sp, modifier = Modifier.align(Alignment.CenterVertically))
        Icon(painterResource(R.drawable.ic_add), "add",
            Modifier
                .padding(start = 10.dp)
                .size(24.dp)
                .align(Alignment.CenterVertically),
            tint = EnjoyComposeTheme.colors.icon
        )
    }
}

@Composable
fun ChatMsgItem(msg: Msg) {
    if(msg.from == User.Me){
        Row(horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()) {
            val bubbleColor = EnjoyComposeTheme.colors.bubbleMe
            Text(
                text = msg.text,
                fontSize = 16.sp,
                color = EnjoyComposeTheme.colors.textPrimaryMe,
                modifier = Modifier
                    .padding(start = 18.dp, end = 0.dp, top = 8.dp, bottom = 8.dp)
                    .drawBehind {
                        val bubblePath = Path().apply {
                            val roundRect = RoundRect(
                                0f, 0f, size.width - 10.dp.toPx(), size.height,
                                4.dp.toPx(), 4.dp.toPx()
                            )
                            addRoundRect(roundRect)
                            moveTo(size.width - 10.dp.toPx(), 10.dp.toPx())
                            lineTo(size.width, 15.dp.toPx())
                            lineTo(size.width - 10.dp.toPx(), 20.dp.toPx())
                            close()
                        }
                        drawPath(bubblePath, bubbleColor)
                    }
                    .padding(8.dp)
                    .padding(end = 10.dp)
            )
            Image(
                painter = painterResource(id = msg.from.avatar),
                contentDescription = msg.from.name,
                modifier = Modifier
                    .padding(8.dp)
                    .size(48.dp)
                    .clip(
                        RoundedCornerShape(4.dp)
                    )
            )
        }
    } else {
        Row(modifier = Modifier.fillMaxWidth()) {
            val bubbleColor = EnjoyComposeTheme.colors.bubbleOthers
            Image(
                painter = painterResource(id = msg.from.avatar),
                contentDescription = msg.from.name,
                modifier = Modifier
                    .padding(8.dp)
                    .size(48.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Text(
                text = msg.text,
                fontSize = 16.sp,
                color = EnjoyComposeTheme.colors.textPrimary,
                modifier = Modifier
                    .padding(start = 0.dp, end = 18.dp, top = 8.dp, bottom = 8.dp)
                    .drawBehind {
                        val bubblePath = Path().apply {
                            val roundRect = RoundRect(
                                10.dp.toPx(), 0f, size.width, size.height,
                                4.dp.toPx(), 4.dp.toPx()
                            )
                            addRoundRect(roundRect)
                            moveTo(10.dp.toPx(), 10.dp.toPx())
                            lineTo(0f, 15.dp.toPx())
                            lineTo(10.dp.toPx(), 20.dp.toPx())
                            close()
                        }
                        drawPath(bubblePath, bubbleColor)
                    }
                    .padding(8.dp)
                    .padding(start = 10.dp)
            )
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