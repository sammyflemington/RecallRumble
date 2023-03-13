package com.csci448.sflemington.recallrumble.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.data.user.User


@Composable
fun UserList(userList: List<User>, leaderboardFlag: Boolean) {
    if (leaderboardFlag == true) {
        LazyColumn {
            items(userList) { user ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "# " + user.rank, fontSize = 28.sp)
                    UserCard(user = user)
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
