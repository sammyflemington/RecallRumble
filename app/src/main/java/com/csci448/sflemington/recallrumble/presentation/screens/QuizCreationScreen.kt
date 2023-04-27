package com.csci448.sflemington.recallrumble.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.csci448.sflemington.recallrumble.data.Question
import com.csci448.sflemington.recallrumble.data.Quiz
import com.csci448.sflemington.recallrumble.presentation.components.QuizCreationQuestion

@Composable
fun QuizCreationScreen(quiz : Quiz, onQuizSaved: (Quiz)->Unit){

    val myQuiz = remember{mutableStateOf(quiz)}
    LazyColumn{

        itemsIndexed(myQuiz.value.questionList) {i, question->
            QuizCreationQuestion(question, i)
            Spacer(Modifier.height(10.dp))
        }
    }

}