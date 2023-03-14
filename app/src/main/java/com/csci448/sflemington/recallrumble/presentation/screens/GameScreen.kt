package com.csci448.sflemington.recallrumble.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
//import com.csci448.sflemington.recallrumble.presentation.components.QuestionDisplayGame
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.csci448.sflemington.recallrumble.data.*
import com.csci448.sflemington.recallrumble.data.user.User
import com.csci448.sflemington.recallrumble.presentation.components.AnswerButton
import com.csci448.sflemington.recallrumble.presentation.components.QuestionDisplayGame
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel
import com.csci448.sflemington.recallrumble.presentation.viewmodel.RRViewModel

@Composable
//Do we want an IViewModel or an RRViewModel?
fun GameScreen(view: IViewModel) {
    val orientation = LocalConfiguration.current.orientation
    val currentContext = LocalContext.current
    Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Seperate this code with a composable passing in each player? Using UserProfileBrief?
            Row() {
                Image(painter = painterResource(id = com.csci448.sflemington.recallrumble.R.drawable.user),contentDescription = null)
                Column() {
                    Text(text = view.currentGame?.player1?.username ?: "null",fontSize = 20.sp,color = MaterialTheme.colorScheme.primary)
                    Row() {
                        Text(
                            text = "Score: ",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = view.currentGame?.player1Score.toString(),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Text(text = "VS", color = Color.Red)
                Image(painter = painterResource(id = com.csci448.sflemington.recallrumble.R.drawable.user),contentDescription = null)
                Column() {
                    Text(
                        text = view.currentGame?.player2?.username ?: "null",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Row() {
                        Text(
                            text = "Score: ",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = view.currentGame?.player1Score.toString(),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            Text(text = "${view.currentQuestionNumber} / ${view.currentGame?.quiz?.quizQuestionCount}")
            Text(text = stringResource(view.currentGame?.quiz?.category?.category ?: 0))
            QuestionDisplayGame(view)
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
