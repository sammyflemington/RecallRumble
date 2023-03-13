package com.csci448.sflemington.recallrumble.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.sflemington.recallrumble.data.Question
import com.csci448.sflemington.recallrumble.presentation.viewmodel.RRViewModel

@Composable
fun QuestionDisplayGame(view: RRViewModel) {
    Text(text = "Question with answers here")
    //Supposed to show question
    Text(text = "${view.currentQuestionState?.prompt}")
}