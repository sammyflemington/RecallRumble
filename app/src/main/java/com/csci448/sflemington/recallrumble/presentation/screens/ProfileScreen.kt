package com.csci448.sflemington.recallrumble.presentation.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.startActivity
import com.csci448.sflemington.recallrumble.Login
import com.csci448.sflemington.recallrumble.MainActivity
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.data.user.User
import com.csci448.sflemington.recallrumble.presentation.components.UserCard
import com.google.firebase.auth.FirebaseAuth


@Composable
fun ProfileScreen(user: User, viewedUser : User?, onEditProfileClicked : () -> Unit, onViewFriendsClicked : () -> Unit) {
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        if (viewedUser != null && user.uid != viewedUser.uid) {/* TODO: check if this player is in friends list. IF not, show "invite to friends list" instead*/
            UserCard(viewedUser)
            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        "This feature is not supported... yet",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text(text = stringResource(R.string.challenge_friend))
            }
        }else{
            UserCard(user)

            Button(
                onClick = { onEditProfileClicked() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text(text = stringResource(R.string.edit_profile))
            }
            Button(onClick = { onViewFriendsClicked() }) {
                Text(text = stringResource(R.string.view_friends))
            }

            Button(
                onClick = {
                    val auth = FirebaseAuth.getInstance()
                    auth.signOut()
                    val intent = Intent(context, Login::class.java)
                    startActivity(context, intent, null)
                }
            ){
                Text(text = "Sign out")
            }
        }

    }
}