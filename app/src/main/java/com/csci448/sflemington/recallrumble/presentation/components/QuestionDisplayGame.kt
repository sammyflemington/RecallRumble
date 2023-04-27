package com.csci448.sflemington.recallrumble.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.data.QuestionStatus
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel
import com.csci448.sflemington.recallrumble.presentation.viewmodel.RRViewModel

@Composable
fun QuestionDisplayGame(view: IViewModel) {
    val currentContext = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth().background(color= Color.LightGray ).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text =  view.currentQuestionState?.prompt ?: "", fontSize = 23.sp, textAlign = TextAlign.Center)
        view.currentQuestionState?.answerChoices?.forEachIndexed { index, answer ->
                Row() {
                    AnswerButton(
                        buttonText =  answer,
                        onButtonClick = {
                            if (index == view.currentQuestionState!!.correctAnswerIndex) {
                                Toast.makeText(currentContext, "CORRECT! Here you will battle head to head with opponents!", Toast.LENGTH_LONG)
                                    .show()
                            } else {
                                Toast.makeText(currentContext, "INCORRECT Here you will battle head to head with opponents!", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    )


                }
        }
    }
}