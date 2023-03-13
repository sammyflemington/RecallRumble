package com.csci448.sflemington.recallrumble.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.data.Question

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizCreationAnswerSlot(text: String, correctFlag: Boolean, onEditButtonClicked : () -> Unit){
    Card(){
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            IconButton(onClick = onEditButtonClicked ){
                Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
            }
            Text(text = text, fontSize = 16.sp)
        }
    }
}

@Preview
@Composable
fun PreviewQuizCreationAnswerSlot(){
    val text = "Here is the text."
    QuizCreationAnswerSlot(text, false){}
}