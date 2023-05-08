package com.csci448.sflemington.recallrumble.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.presentation.navigation.specs.FriendsScreenSpec

//Landing Screen
@Composable
fun StartGameScreen(onStartGameClicked : () -> Unit, onInviteFriendClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Button(
            onClick = { onStartGameClicked() },
            contentPadding = PaddingValues(20.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = null,
                    Modifier.size(100.dp)
                )
                Text(text = "Play Game!", fontSize = 28.sp)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
//        Button(
//            onClick = {onInviteFriendClicked()},
//            contentPadding = PaddingValues(20.dp),
//            shape = RoundedCornerShape(10.dp)
//        ){
//            Text(text = "Challenge a Friend", fontSize = 20.sp)
//        }
    }
}

@Preview
@Composable
fun PreviewStartGameScreen() {
    StartGameScreen(onStartGameClicked = {}, onInviteFriendClicked = {})
}