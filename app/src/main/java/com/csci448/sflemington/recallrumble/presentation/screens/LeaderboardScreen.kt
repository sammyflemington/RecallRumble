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
fun LeaderboardScreen(leaderBoard: List<User>, navController : NavHostController, viewModel : IViewModel) {
    Column() {
        Text(text = "Leaderboard", fontSize = 28.sp)
        UserList(userList = leaderBoard, leaderboardFlag = true, navController = navController, viewModel = viewModel)
    }
}