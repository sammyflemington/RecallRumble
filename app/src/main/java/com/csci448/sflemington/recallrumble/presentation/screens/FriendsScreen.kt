package com.csci448.sflemington.recallrumble.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.data.user.User
import com.csci448.sflemington.recallrumble.presentation.components.UserList


@Composable
fun FriendsScreen(user: User) {
    Column() {
        Text(text = "Friends", fontSize = 28.sp)
        UserList(userList = user.friends, leaderboardFlag = false)
    }
}

