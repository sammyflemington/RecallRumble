package com.csci448.sflemington.recallrumble.data

import androidx.compose.runtime.mutableStateOf

object QuizRepository {
    val quiz1 = Quiz(
        title = "My Quiz",
        questionList = listOf(
            QuestionRepository.moviesList[0]
        ),
        category = CategoryRepository.categoryList[5]
    )

    val newQuiz = MutableQuiz(
        title = mutableStateOf("New Quiz"),
        questionList = listOf(
            MutableQuestion(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0),
            MutableQuestion(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0),
            MutableQuestion(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0),
            MutableQuestion(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0),
            MutableQuestion(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0)
        ),
        category = mutableStateOf(CategoryRepository.categoryList[5])
    )
}