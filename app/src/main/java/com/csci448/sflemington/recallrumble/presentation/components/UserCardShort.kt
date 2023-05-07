package com.csci448.sflemington.recallrumble.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.data.user.User


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCardShort(user: User, onUserCardClicked : (User) -> Unit) {
    Card(modifier = Modifier.padding(8.dp), onClick = {onUserCardClicked(user)}) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer)) {
            // profile picture
            Image(painter = painterResource(id = R.drawable.user),contentDescription = null)
            // user info
            Column(Modifier.padding(4.dp)) {
                Text(text = user.username,fontSize = 20.sp,color = MaterialTheme.colorScheme.primary)
                Text(text = user.name,fontSize = 16.sp,color = MaterialTheme.colorScheme.primary)
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp, bottom = 0.dp, start = 0.dp, end = 0.dp)
                        .background(color = MaterialTheme.colorScheme.primaryContainer)) {
                    Text(text = "Rating: ",fontSize = 16.sp,color = MaterialTheme.colorScheme.primary)
                    Text(text = (user.bestScoresByCategory.values.sum() / user.bestScoresByCategory.values.size).toString(),fontSize = 16.sp,color = MaterialTheme.colorScheme.primary)
                }
            }

        }


    }
}
