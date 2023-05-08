package com.csci448.sflemington.recallrumble.presentation.screens

import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
//import com.csci448.sflemington.recallrumble.presentation.components.QuestionDisplayGame
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.data.*
import com.csci448.sflemington.recallrumble.data.user.User
import com.csci448.sflemington.recallrumble.presentation.components.AnswerButton
import com.csci448.sflemington.recallrumble.presentation.components.QuestionDisplayGame
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel
import com.csci448.sflemington.recallrumble.presentation.viewmodel.RRViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
//Do we want an IViewModel or an RRViewModel?
fun GameScreen(view: IViewModel, onCorrect : (Float) -> Unit, onWrong : () -> Unit) {
    val orientation = LocalConfiguration.current.orientation
    val currentContext = LocalContext.current
    val mMediaPlayerDing = MediaPlayer.create(currentContext, R.raw.ding)
    val mMediaPlayerBoo = MediaPlayer.create(currentContext, R.raw.boo)
    val totalTime : Float = 15.0f * 1000f // 10 seconds
    val timerValue = remember{mutableStateOf(10.0f)}
    val currentTime = remember{mutableStateOf(totalTime)}

    val composableScope = rememberCoroutineScope()
    LaunchedEffect(key1 = view.currentGame){
        composableScope.launch {
            while (currentTime.value > 0){
                delay(100L)
                currentTime.value -= 100L
                timerValue.value = currentTime.value / totalTime.toFloat()
                if (currentTime.value <= 0){
                    onWrong()
                    mMediaPlayerBoo.start()
                    currentTime.value = totalTime
                }
            }

        }
    }


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Seperate this code with a composable passing in each player? Using UserProfileBrief?
        Row() {
            Card(
                modifier = Modifier
                    .width(180.dp)
                    .padding(5.dp)
            ) {
                Row() {
                    Image(
                        painter = painterResource(id = com.csci448.sflemington.recallrumble.R.drawable.user),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                    Column() {
                        Text(
                            text = view.currentGame?.player1?.username ?: "null",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Row() {
                            Text(
                                text = "Score: ",
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = view.currentScore.toString(),
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
        Row() {
            //Time
        }
            //Text(text = stringResource(view.currentGame?.quiz?.category?.category ?: 0), fontSize = 33.sp)
//            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(64.dp, 64.dp)){
//                Canvas(modifier = Modifier.size(64.dp)){
//                    drawArc(
//                        color = Color.Blue,
//                        startAngle = -215f,
//                        sweepAngle = 250f * timerValue.value,
//                        useCenter = false,
//                        size = Size(64f, 64f)
//                    )
//                }
//            }
            Text((currentTime.value / 1000f).roundToInt().toString(), fontSize = 24.sp)
            Text(text = "${view.currentQuestionNumber} / ${view.currentGame?.quiz?.quizQuestionCount}", fontSize = 23.sp)

            QuestionDisplayGame(
                view,
                onCorrectAnswer = {
                    onCorrect(timerValue.value)
                    currentTime.value = totalTime
                    mMediaPlayerDing.start()
                                  },
                onWrongAnswer = {
                    onWrong()
                    currentTime.value = totalTime
                    mMediaPlayerBoo.start()
                })
    }
}

@Preview
@Composable
fun PreviewGameScreen() {
//    val player1 = User(name= "lexi", username = "lc", null, 4, 3, 1)
//    val player2 = User(name= "jack", username = "jl", null, 5, 23, 2)
//    val leaderboard = listOf<User>(player1, player2)
//    val view = RRViewModel(player1, leaderboard)
//    //val question = Question(answerChoices = listOf("", " "))
//    val questions = listOf<Question>(Question(answerChoices = listOf(1, 2)), Question(answerChoices = listOf("", " ")), Question(answerChoices = listOf("", " ")))
//    val quiz = Quiz("myQuiz", questions, Category(com.csci448.sflemington.recallrumble.R.string.category_geography, com.csci448.sflemington.recallrumble.R.drawable.geography_graphic, emptyList()))
//    val quizPlay = QuizPlay(quiz, player1, player2)
//    view.newQuizPlay(quizPlay)
//    GameScreen(view)
}
