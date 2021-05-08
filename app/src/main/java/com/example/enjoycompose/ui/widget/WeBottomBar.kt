package com.example.enjoycompose.ui.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.enjoycompose.R
import com.example.enjoycompose.WeViewModel
import com.example.enjoycompose.ui.theme.EnjoyComposeTheme

@Composable
fun WeBottomBar(selected: Int) {
    val viewModel: WeViewModel = viewModel()
    Row(Modifier.background(EnjoyComposeTheme.colors.bottomBar)) {
        TabItem(
            if (selected == 0) R.drawable.ic_chat_filled else R.drawable.ic_chat_outlined,
            "聊天",
            if (selected == 0) EnjoyComposeTheme.colors.iconCurrent else EnjoyComposeTheme.colors.icon,
            Modifier
                .clickable {
                    viewModel.selectedTab = 0
                }
                .weight(1f)
        )
        TabItem(
            if (selected == 1) R.drawable.ic_contacts_filled else R.drawable.ic_contacts_outlined,
            "通讯录",
            if (selected == 1) EnjoyComposeTheme.colors.iconCurrent else EnjoyComposeTheme.colors.icon,
            Modifier
                .clickable {
                    viewModel.selectedTab = 1
                }
                .weight(1f)
        )
        TabItem(if (selected == 2) R.drawable.ic_discover_filled else R.drawable.ic_discover_outlined,
            "发现",
            if (selected == 2) EnjoyComposeTheme.colors.iconCurrent else EnjoyComposeTheme.colors.icon,
            Modifier
                .clickable {
                    viewModel.selectedTab = 2
                }
                .weight(1f)
        )
        TabItem(
            if (selected == 3) R.drawable.ic_me_filled else R.drawable.ic_me_outlined,
            "我",
            if (selected == 3) EnjoyComposeTheme.colors.iconCurrent else EnjoyComposeTheme.colors.icon,
            Modifier
                .clickable {
                    viewModel.selectedTab = 3
                }
                .weight(1f)
        )
    }
}

@Composable
private fun TabItem(@DrawableRes iconId: Int, title: String, tint: Color, modifier: Modifier) {
    Column(modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painterResource(iconId), title,
            Modifier.size(24.dp), tint = tint
        )
        Text(title, fontSize = 11.sp, color = tint)
    }
}

@Preview(showBackground = true)
@Composable
fun WeBottomBarPreviewNewYear() {
    EnjoyComposeTheme(EnjoyComposeTheme.Theme.NewYear){
        WeBottomBar(0)
    }
}

@Preview(showBackground = true)
@Composable
fun WeBottomBarPreviewLight() {
    EnjoyComposeTheme(EnjoyComposeTheme.Theme.Light){
        WeBottomBar(0)
    }
}

@Preview(showBackground = true)
@Composable
fun WeBottomBarPreviewDark() {
    EnjoyComposeTheme(EnjoyComposeTheme.Theme.Dark){
        WeBottomBar(0)
    }
}

@Preview(showBackground = true)
@Composable
fun TabItemPreview() {
    TabItem(iconId = R.drawable.ic_chat_filled, title = "聊天", EnjoyComposeTheme.colors.iconCurrent, modifier = Modifier)
}