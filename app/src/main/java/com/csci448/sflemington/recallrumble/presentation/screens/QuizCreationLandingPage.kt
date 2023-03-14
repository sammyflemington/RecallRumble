package com.csci448.sflemington.recallrumble.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.data.Question
import com.csci448.sflemington.recallrumble.presentation.components.QuizCreationQuestion

@Composable
fun QuizCreationLandingPage(onCreateQuizClicked: () -> Unit, onViewQuizzesClicked : ()->Unit){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Button(
            onClick = { onCreateQuizClicked() },
            contentPadding = PaddingValues(20.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Create a Quiz", fontSize = 28.sp)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {onViewQuizzesClicked()},
            contentPadding = PaddingValues(20.dp),
            shape = RoundedCornerShape(10.dp)
        ){
            Text(text = "View my quizzes", fontSize = 20.sp)
        }
    }

}

@Preview
@Composable
fun Preview(){
    QuizCreationLandingPage(onViewQuizzesClicked = {}, onCreateQuizClicked = {})
}