package com.csci448.sflemington.recallrumble.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.csci448.sflemington.recallrumble.data.user.User
import com.csci448.sflemington.recallrumble.presentation.components.UserCard


@Composable
fun ProfileScreen(user: User) {
    Column() {
        UserCard(user)
        Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)) {
            Text(text = "Edit Profile")
        }
    }
}