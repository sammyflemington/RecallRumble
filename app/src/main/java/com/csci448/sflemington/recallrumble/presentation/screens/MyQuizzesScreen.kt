package com.csci448.sflemington.recallrumble.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.data.CategoryRepository
import com.csci448.sflemington.recallrumble.data.Quiz
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel

@Composable
fun MyQuizzesScreen(viewModel : IViewModel, myQuizzes: List<Quiz>, onQuizClicked: (Quiz)->Unit, onCreateQuiz: ()->Unit){

    LazyColumn(modifier = Modifier.padding(8.dp)){
        if (myQuizzes.isEmpty()){
            item{
                Spacer(modifier = Modifier.height(8.dp))
                Text("You haven't created any quizzes!")
                Button(onClick = {onCreateQuiz()}, modifier = Modifier.fillMaxWidth().padding(8.dp)){
                    Text("Create one!")
                }
            }

        }
        itemsIndexed(myQuizzes){i, quiz->
            Spacer(modifier = Modifier.height(8.dp))
            MyQuizzesListItem(quiz = quiz){onQuizClicked(quiz)}
        }
    }
}

@Composable
fun MyQuizzesListItem(quiz: Quiz, onClick: (Quiz)->Unit){
    Card(modifier = Modifier.fillMaxWidth().clickable{onClick(quiz)}){
        Row(){
            Spacer(modifier = Modifier.width(8.dp))
            Column(){
                Text(quiz.title, fontSize = 24.sp)
                Text(stringResource(quiz.category.category), fontSize = 16.sp)
            }

                Column(verticalArrangement = Arrangement.Center){
                    Spacer(modifier = Modifier.height(8.dp).align(Alignment.End).fillMaxWidth())
                    Text(quiz.plays.toString() + " play"+ if (quiz.plays != 1) "s" else "", modifier = Modifier.align(Alignment.End).padding(8.dp))
                }



//            Box(modifier = Modifier.fillMaxHeight().align(Alignment.CenterVertically)){
//                Text("Edit")
//            }

            //Icon(Icons.Filled.ArrowForward, contentDescription="My Quiz")
        }

    }
}

@Preview
@Composable
fun PreviewMyQUizzesListItem(){
    val quiz = Quiz(title="My quiz", category  = CategoryRepository.categoryList[0])
    MyQuizzesListItem(quiz){}
}