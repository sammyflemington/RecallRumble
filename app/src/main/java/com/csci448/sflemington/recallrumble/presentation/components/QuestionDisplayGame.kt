package com.csci448.sflemington.recallrumble.presentation.components

import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.data.QuestionStatus
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel
import com.csci448.sflemington.recallrumble.presentation.viewmodel.RRViewModel

@Composable
fun QuestionDisplayGame(view: IViewModel, onCorrectAnswer : ()->Unit, onWrongAnswer: ()->Unit) {
    val currentContext = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth().background(color= Color.LightGray ).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        //Text(text =  view.currentQuestionState?.prompt ?: "Quiz not found", fontSize = 23.sp, textAlign = TextAlign.Center)
        view.currentQuestionState?.answerChoices?.forEachIndexed { index, answer ->
                Row() {
                    AnswerButton(
                        buttonText =  answer,
                        onButtonClick = {
                            if (index == view.currentQuestionState!!.correctAnswerIndex) {
                                onCorrectAnswer()
                                Toast.makeText(currentContext, "CORRECT! Here you will battle head to head with opponents!", Toast.LENGTH_LONG)
                                    .show()
                            } else {
                                onWrongAnswer()
                                Toast.makeText(currentContext, "INCORRECT Here you will battle head to head with opponents!", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    )


                }
        }
    }
}

//@Composable
//fun GladiatorAnimation(view: IViewModel) {
//    val animatedSizeDp: Dp by animateDpAsState(targetValue = if (view.isNeedExpansion) 350.dp else 100.dp)
//
//    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//
//        Button(
//            onClick = {
//                isNeedExpansion.value = !isNeedExpansion.value
//            },
//            modifier = Modifier
//                .padding(top = 50.dp)
//                .width(300.dp)
//        ) {
//            Text(text = "animateDpAsState")
//        }
//    }
//}
//
//@Composable
//fun QuestionCard(imageSize: Dp) {
//    Image(
//        painter = painterResource(R.drawable.andy_rubin),
//        contentDescription = "Circle Image",
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//            .size(imageSize)
//            .clip(CircleShape)
//            .border(5.dp, Color.Gray, CircleShape)
//    )
//}