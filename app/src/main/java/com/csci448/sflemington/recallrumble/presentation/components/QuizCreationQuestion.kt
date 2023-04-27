package com.csci448.sflemington.recallrumble.presentation.components

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.data.Question
import com.csci448.sflemington.recallrumble.data.QuestionRepository

@Composable
fun QuizCreationQuestion(question: Question, myNumber : Int){
    val prompt = rememberSaveable{mutableStateOf(question.prompt)}
    val answers = rememberSaveable{listOf(mutableStateOf(question.answerChoices[0]),
        mutableStateOf(question.answerChoices[1]),
        mutableStateOf(question.answerChoices[2]),
        mutableStateOf(question.answerChoices[3]))}
    val correctAnswerIndex = rememberSaveable {mutableStateOf(question.correctAnswerIndex)}
//    val notif = rememberSaveable { mutableStateOf("") }
//    if (notif.value.isNotEmpty()) {
//        Toast.makeText(LocalContext.current, notif.value, Toast.LENGTH_LONG).show()
//        notif.value = ""
//    }

    Row(){
        //Text("#".plus(myNumber.toString()))
        Column (Modifier.padding(10.dp)){
            QuizCreationPrompt(prompt.value, myNumber, onEditButtonClicked = {})
            for (i in 0..3){
                Row(Modifier.padding(10.dp)) {
                    QuizCreationAnswerSlot(answers[i].value, i, correctAnswerIndex.value, onCorrectAnswerClicked = {correctAnswerIndex.value = i})
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewQuizCreationQuestion(){
    QuizCreationQuestion(QuestionRepository.geographyList[0], 1)
}