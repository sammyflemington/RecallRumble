package com.csci448.sflemington.recallrumble.presentation.screens

import android.media.MediaPlayer
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel

@Composable
fun GameResultScreen(view: IViewModel) {
    val mMediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.crowd)
    mMediaPlayer.start()
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Results", fontSize = 50.sp)
        Text(text = "Quiz: " + view.currentGame?.quiz?.title, fontSize = 20.sp)
        Text(text = "Category: " + stringResource(id = view.currentGame?.quiz?.category?.category!!), fontSize = 20.sp)
        Text(text = "Created By: " + view.currentGameCreator, fontSize = 20.sp)
        Text(text = "Final Score", fontSize = 20.sp)
        Text(
            text = view.currentScore.toString(),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(50.dp))
        PulsatingMen()
    }
}

@Composable
fun PulsatingMen() {
    val infiniteTransition = rememberInfiniteTransition()

    val gladiator by infiniteTransition.animateFloat(
        initialValue = 100.0f,
        targetValue = 250.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, delayMillis = 100,easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        painter = painterResource(R.drawable.gladiator_win),
        contentDescription = "gladiators pulsing",
        modifier = Modifier
            .size(gladiator.dp)
            .rotate(gladiator * 4)
    )
}
