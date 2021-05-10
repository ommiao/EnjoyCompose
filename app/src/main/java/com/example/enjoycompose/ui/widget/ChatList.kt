package com.example.enjoycompose.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.enjoycompose.WeViewModel
import com.example.enjoycompose.ui.theme.EnjoyComposeTheme
import com.rengwuxian.wecompose.data.Chat

@Composable
fun ChatList(chats: List<Chat>) {
    Column(
        Modifier
            .background(EnjoyComposeTheme.colors.background)
            .fillMaxSize()
    ) {
        WeTopBar(title = "X信", onBack = null)
        LazyColumn(
            Modifier
                .background(EnjoyComposeTheme.colors.listItem)
        ) {
            itemsIndexed(chats) { index, chat ->
                ChatListItem(chat)
                if(index < chats.size - 1){
                    Divider(
                        startIndent = 68.dp,
                        color = EnjoyComposeTheme.colors.chatListDivider,
                        thickness = 0.8f.dp
                    )
                }
            }
        }
    }
}

@Composable
private fun ChatListItem(chat: Chat) {
    val viewModel: WeViewModel = viewModel()
    Row(modifier = Modifier
        .clickable {
        viewModel.startChat(chat) }
//        .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = chat.friend.avatar),
            contentDescription = chat.friend.name,
            Modifier
                .padding(8.dp)
                .size(48.dp)
                .unread(!chat.msgs.last().read, EnjoyComposeTheme.colors.badge)
                .clip(RoundedCornerShape(4.dp))

        )
        Column(Modifier.padding(8.dp)) {
            Row() {
                Text(
                    text = chat.friend.name,
                    fontSize = 17.sp,
                    color = EnjoyComposeTheme.colors.textPrimary,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = chat.msgs.last().time,
                    fontSize = 11.sp,
                    color = EnjoyComposeTheme.colors.textSecondary
                )
            }
            Text(
                text = chat.msgs.last().text,
                fontSize = 14.sp,
                color = EnjoyComposeTheme.colors.textSecondary
            )
        }
    }
}


fun Modifier.unread(show: Boolean, badgeColor: Color) : Modifier = this.drawWithContent {
    drawContent()
    if(show){
        drawIntoCanvas { canvas ->
        val paint = Paint().apply {
            color = badgeColor
        }
        canvas.drawCircle(Offset(size.width - 1.dp.toPx(),
            1.dp.toPx()), 5.dp.toPx(), paint)
        }
    }

}

@Preview
@Composable
fun ChatListPreview() {
    ChatList(chats = WeViewModel().chats)
}