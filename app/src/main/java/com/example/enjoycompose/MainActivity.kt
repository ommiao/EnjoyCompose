package com.example.enjoycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.enjoycompose.ui.theme.EnjoyComposeTheme
import com.example.enjoycompose.ui.widget.ChatList
import com.example.enjoycompose.ui.widget.Pager
import com.example.enjoycompose.ui.widget.PagerState
import com.example.enjoycompose.ui.widget.WeBottomBar

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: WeViewModel = viewModel()
            EnjoyComposeTheme(viewModel.theme) {
                Column {
                    val pagerState: PagerState = kotlin.run {
                        remember() {
                            PagerState(maxPage = 3)
                        }
                    }
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
        }
    }

}