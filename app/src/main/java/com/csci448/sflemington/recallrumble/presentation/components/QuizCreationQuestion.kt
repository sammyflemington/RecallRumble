package com.csci448.sflemington.recallrumble.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.data.Question
import com.csci448.sflemington.recallrumble.data.QuestionRepository

@Composable
fun QuizCreationQuestion(question: Question, myNumber : Int){
    val prompt = rememberSaveable{mutableStateOf("Question")}
    val answers = rememberSaveable{listOf(mutableStateOf("Answer 1"),
        mutableStateOf("Answer 2"),
        mutableStateOf("Answer 3"),
        mutableStateOf("Answer 4"))}
    val correctAnswerIndex = rememberSaveable {mutableStateOf(0)}

    Row(){
        //Text("#".plus(myNumber.toString()))
        Column{
            QuizCreationPrompt(prompt.value, myNumber, onEditButtonClicked = {})
            for (i in 0..3){
                QuizCreationAnswerSlot(answers[i].value, i, correctAnswerIndex.value, onCorrectAnswerClicked = {correctAnswerIndex.value = i})
            }
        }
    }
}

@Preview
@Composable
fun PreviewQuizCreationQuestion(){
    QuizCreationQuestion(QuestionRepository.geographyList[0], 1)
}