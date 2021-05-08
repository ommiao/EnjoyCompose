package com.rengwuxian.wecompose.data

import androidx.annotation.DrawableRes
import com.example.enjoycompose.R

data class User(
  val id: String,
  val name: String,
  @DrawableRes val avatar: Int
) {
  companion object {
    val Me: User = User("laoreng", "老扔", R.drawable.avatar_rengwuxian)
  }
}