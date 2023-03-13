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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.data.user.User


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCard(user: User) {
    Card(modifier = Modifier.padding(8.dp), onClick = {}) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer)) {
            Image(painter = painterResource(id = R.drawable.user),contentDescription = null)
            Column(Modifier.padding(4.dp)) {
                Text(text = user.name,fontSize = 28.sp,color = MaterialTheme.colorScheme.primary)
                Text(text = user.username,fontSize = 20.sp,color = MaterialTheme.colorScheme.primary)
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer)) {
            Text(text = "Rank: ",fontSize = 16.sp,color = MaterialTheme.colorScheme.primary)
            Text(text = user.rank.toString(),fontSize = 16.sp,color = MaterialTheme.colorScheme.primary)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer)) {
            Text(text = "Games Won: ",fontSize = 16.sp,color = MaterialTheme.colorScheme.primary)
            Text(text = user.gamesWon.toString(),fontSize = 16.sp,color = MaterialTheme.colorScheme.primary)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer)) {
            Text(text = "Games Lost: ",fontSize = 16.sp,color = MaterialTheme.colorScheme.primary)
            Text(text = user.gamesLost.toString(),fontSize = 16.sp,color = MaterialTheme.colorScheme.primary)
        }
    }
}
