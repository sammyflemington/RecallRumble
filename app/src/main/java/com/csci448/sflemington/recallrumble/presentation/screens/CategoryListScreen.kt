package com.csci448.sflemington.recallrumble.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.data.*
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel
import com.csci448.sflemington.recallrumble.presentation.viewmodel.RRViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//RRViewModel?
fun CategoryListScreen(categories: List<Category>, selectCategory: () -> Unit, view: IViewModel) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.width(300.dp)
        ) {
            items(categories) {
                Card(modifier = Modifier
                        .padding(11.dp)
                        .height(122.dp)
                    .clickable {
                        //val questions = listOf<Question>(Question(answerChoices = listOf("Apple", "Banana")), Question(answerChoices = listOf("", " ")), Question(answerChoices = listOf("", " ")))
                        val quiz = Quiz("myQuiz", it.questionList, it)
                        val quizPlay = QuizPlay(quiz, view.user, view.leaderBoard[0])
                        view.newQuizPlay(quizPlay)
                        selectCategory()
                    }
                ) {
                    Column(Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Text(
                            text = stringResource(id = it.category),
                            fontSize = 23.sp,
                            textAlign = TextAlign.Center
                        )
                        Image(painter = painterResource(id = it.graphic), contentDescription = "")
                    }
                }
            }
        }
}


@Preview
@Composable
fun PreviewCategoryListScreen() {
    val previewList = CategoryRepository.categoryList
    //CategoryListScreen(categories = previewList)
}
