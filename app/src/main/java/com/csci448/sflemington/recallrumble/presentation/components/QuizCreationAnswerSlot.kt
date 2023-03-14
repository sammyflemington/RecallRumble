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
import com.csci448.sflemington.recallrumble.data.Quiz

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizCreationAnswerSlot(text: String, answerIndex: Int, onEditButtonClicked : () -> Unit){
    var option by rememberSaveable { mutableStateOf(text) }
    Card(){
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            IconButton(onClick = onEditButtonClicked ){
                Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
            }
            Column() {
                Text(text = "Option " + (answerIndex + 1), fontSize = 16.sp)
                TextField(value = option, onValueChange = {option = it})
            }
        }
    }
}

@Preview
@Composable
fun PreviewQuizCreationAnswerSlot(){
    val text = "Here is the text."
    QuizCreationAnswerSlot(text, 0){}
}