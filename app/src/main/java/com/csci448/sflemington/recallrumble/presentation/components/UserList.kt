package com.csci448.sflemington.recallrumble.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            itemsIndexed(userList) {i, user ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "# " + (i+1).toString(), fontSize = 20.sp, modifier = Modifier.width(48.dp))
                    UserCardShort(user = user, onUserCardClicked = {
                        viewModel.setViewedUser(user)
                        navController.navigate(ProfileScreenSpec.buildRoute(user.uid.toString()))
                    })
                }
            }
        }
    } else {
        LazyColumn {
            items(userList) { user ->
                UserCardShort(user = user, onUserCardClicked = {
                    viewModel.setViewedUser(user)
                    navController.navigate(ProfileScreenSpec.buildRoute(user.uid.toString()))
                })
            }
        }
    }


}
