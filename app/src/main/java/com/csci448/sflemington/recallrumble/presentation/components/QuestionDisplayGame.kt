package com.csci448.sflemington.recallrumble.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.csci448.sflemington.recallrumble.presentation.viewmodel.RRViewModel

@Composable
fun QuestionDisplayGame(view: RRViewModel) {
    //Text(text = "Question with answers here")
    //Supposed to show question
    val currentContext = LocalContext.current
    Column() {
        Text(text = "${view.currentQuestionState?.prompt}")
        view.currentQuestionState?.answerChoices?.forEachIndexed { index, answer ->
            Row() {
                AnswerButton(
                    buttonText = answer,
                    onButtonClick = {
                        if(index == view.currentQuestionState!!.correctAnswerIndex) {
                            Toast.makeText(currentContext, "BADASS", Toast.LENGTH_SHORT)
                            .show()
                            }
                    }
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