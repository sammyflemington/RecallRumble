package com.csci448.sflemington.recallrumble.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.data.Question
import com.csci448.sflemington.recallrumble.data.Quiz

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizCreationAnswerSlot(text: MutableState<String>, answerIndex: Int, correctAnswerIndex : Int, onCorrectAnswerClicked : () -> Unit){
    Card(colors = CardDefaults.cardColors(
        containerColor = if (answerIndex == correctAnswerIndex)
            MaterialTheme.colorScheme.surfaceTint
        else
            MaterialTheme.colorScheme.surfaceVariant

    )){
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            IconButton(onClick = onCorrectAnswerClicked ){
                Icon(imageVector = if (answerIndex == correctAnswerIndex)
                    Icons.Filled.Check
                else
                    Icons.Default.Close
                    , contentDescription = null)
            }
            Column() {
                Text(text = "Option " + (answerIndex + 1), fontSize = 16.sp)
                TextField(value = text.value, onValueChange = {text.value = it})
            }
        }
    }
}

@Preview
@Composable
fun PreviewQuizCreationAnswerSlot(){
    val text = "Here is the text."
    //QuizCreationAnswerSlot(text, 0, 0){}
}