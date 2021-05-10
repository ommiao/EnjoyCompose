package com.example.enjoycompose.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.enjoycompose.WeViewModel

@Composable
fun Home() {
    Column {
        val pagerState: PagerState = kotlin.run {
            remember() {
                PagerState(maxPage = 3)
            }
        }
        val viewModel: WeViewModel = viewModel()
        Pager(state = pagerState, Modifier.weight(1f)) {
            when (page) {
                0 -> ChatList(viewModel.chats)
                1 -> Box(modifier = Modifier.fillMaxSize())
                2 -> Box(modifier = Modifier.fillMaxSize())
                3 -> Box(modifier = Modifier.fillMaxSize())
            }
        }
        WeBottomBar(viewModel.selectedTab)
    }
}