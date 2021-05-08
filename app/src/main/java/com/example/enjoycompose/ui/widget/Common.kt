package com.example.enjoycompose.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.enjoycompose.R
import com.example.enjoycompose.WeViewModel
import com.example.enjoycompose.ui.theme.EnjoyComposeTheme

@Composable
fun WeTopBar(title: String, onBack: (() -> Unit)?) {
    Box(
        Modifier
            .background(EnjoyComposeTheme.colors.background)
            .height(48.dp)
            .fillMaxWidth()
    ){
        Row(Modifier.align(Alignment.CenterStart)) {
            if(onBack != null){
                Icon(
                    painterResource(id = R.drawable.ic_back),
                    contentDescription = "返回",
                    tint = EnjoyComposeTheme.colors.icon,
                    modifier = Modifier
                        .size(36.dp)
                        .padding(8.dp)
                        .align(CenterVertically)
                        .clickable(onClick = onBack)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            val viewModel: WeViewModel = viewModel()
            Icon(
                painterResource(id = R.drawable.ic_palette),
                contentDescription = "换肤",
                tint = EnjoyComposeTheme.colors.icon,
                modifier = Modifier
                    .size(36.dp)
                    .padding(8.dp)
                    .align(CenterVertically)
                    .clickable(onClick = {
                        viewModel.theme = when(viewModel.theme){
                            EnjoyComposeTheme.Theme.Light -> EnjoyComposeTheme.Theme.Dark
                            EnjoyComposeTheme.Theme.Dark -> EnjoyComposeTheme.Theme.NewYear
                            EnjoyComposeTheme.Theme.NewYear -> EnjoyComposeTheme.Theme.Light
                        }
                    })
            )
        }
        Text(
            text = title,
            color = EnjoyComposeTheme.colors.textPrimary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun WeTopBarPreview() {
    WeTopBar("瞎整的标题", onBack = {

    })
}