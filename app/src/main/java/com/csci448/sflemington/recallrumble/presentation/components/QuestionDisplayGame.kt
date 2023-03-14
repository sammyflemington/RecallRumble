package com.csci448.sflemington.recallrumble.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.csci448.sflemington.recallrumble.data.QuestionStatus
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel
import com.csci448.sflemington.recallrumble.presentation.viewmodel.RRViewModel

@Composable
fun QuestionDisplayGame(view: IViewModel) {
    //Text(text = "Question with answers here")
    //Supposed to show question
    val defaultButton = ButtonDefaults.buttonColors()
    val correctButton = ButtonDefaults.buttonColors(
        disabledContainerColor = Color.Green,
        disabledContentColor = Color.Black)
    val incorrectButton = ButtonDefaults.buttonColors(
        disabledContainerColor = Color.Red,
        disabledContentColor = Color.Black)

    val currentContext = LocalContext.current
    Column() {
        Text(text = stringResource(id = view.currentQuestionState?.prompt ?: 0))
        view.currentQuestionState?.answerChoices?.forEachIndexed { index, answer ->
            Row() {
                AnswerButton(
                    buttonText = stringResource(id = answer),
                    onButtonClick = {
                        if(index == view.currentQuestionState!!.correctAnswerIndex) {
                            //view.updateStatus(true)
                            Toast.makeText(currentContext, "BADASS", Toast.LENGTH_SHORT)
                            .show()
                        }
                        else {
                            //view.updateStatus(false)
                            Toast.makeText(currentContext, "Not so badass", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
//                    if (view.currentQuestionStatus == QuestionStatus.ANSWERED_CORRECT && index == view.currentQuestionState!!.correctAnswerIndex) {
//                        correctButton
//                    } else if (view.currentQuestionStatus == QuestionStatus.ANSWERED_INCORRECT && index == view.currentQuestionState!!.correctAnswerIndex) {
//                        incorrectButton
//                    } else {
//                        defaultButton
//                    }
                )


            }
        }
    }
}

//fun checkAnswer(choice: Int, correctAnswerIndex: Int) {
//
//}

//checkAnswerChoice(
//choice,
//question.correctChoiceNumber,
//questionStatus,
//onCorrectAnswer,
//onWrongAnswer,
//onCheatAnswer
//)
//},
//if (questionStatus == QuestionStatus.ANSWERED_CORRECT && choice == question.correctChoiceNumber) {
//    correctButtonColors
//} else if (questionStatus == QuestionStatus.ANSWERED_INCORRECT && choice == question.correctChoiceNumber) {
//    incorrectButtonColors
//} else {
//    defaultButtonColors
//},
//questionStatus == QuestionStatus.UNANSWERED || questionStatus == QuestionStatus.CHEATED
//)