package com.csci448.sflemington.recallrumble.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.csci448.sflemington.recallrumble.data.user.User
import com.csci448.sflemington.recallrumble.presentation.components.UserList
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel


@Composable
fun FriendsScreen(user: User, viewModel : IViewModel, navController: NavHostController) {
    Column() {
        Text(text = "Friends", fontSize = 28.sp)
        user.friends?.let { UserList(userList = it, leaderboardFlag = false, navController = navController, viewModel = viewModel) }
    }
}

