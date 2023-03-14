package com.csci448.sflemington.recallrumble.data

object QuizRepository {
    val quiz1 = Quiz(
        title = "My Quiz",
        questionList = listOf(
            QuestionRepository.moviesList[0]
        ),
        category = CategoryRepository.categoryList[5]
    )
}