package com.example.enjoycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.enjoycompose.ui.theme.EnjoyComposeTheme
import com.example.enjoycompose.ui.widget.*

class MainActivity : ComponentActivity() {

    val viewModel: WeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: WeViewModel = viewModel()
            EnjoyComposeTheme(viewModel.theme) {
                Home()
                ChatPage()
            }
        }
    }

    override fun onBackPressed() {
        if(viewModel.chatting){
            viewModel.endChat()
        } else {
            super.onBackPressed()
        }
    }

}