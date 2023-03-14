package com.csci448.sflemington.recallrumble.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.csci448.sflemington.recallrumble.data.user.User
import com.csci448.sflemington.recallrumble.presentation.navigation.specs.ProfileScreenSpec
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel


@Composable
fun UserList(userList: List<User>, leaderboardFlag: Boolean, navController : NavHostController, viewModel : IViewModel) {
    if (leaderboardFlag == true) {
        LazyColumn {
            items(userList) { user ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "# " + user.rank, fontSize = 28.sp)
                    UserCardShort(user = user, onUserCardClicked = {
                        viewModel.setViewedUser(user)
                        navController.navigate(ProfileScreenSpec.buildRoute(user.id.toString()))
                    })
                }
            }
        }
    } else {
        LazyColumn {
            items(userList) { user ->
                UserCard(user)
            }
        }
    }


}
