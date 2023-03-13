package com.csci448.sflemington.recallrumble.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.sflemington.recallrumble.data.Question

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizCreationAnswerSlot(text: String, onEditButtonClicked : () -> Unit){
    Card(){
        Row(){
            Text(text = text)
//            IconButton(onClick = onEditButtonClicked){
//                Icon(imageVector = Icons.Filled.Edit)
//            }
        }


    }
}

@Preview
@Composable
fun PreviewQuizCreationAnswerSlot(){
    val text = "Here is the text."
    QuizCreationAnswerSlot(text){}
}