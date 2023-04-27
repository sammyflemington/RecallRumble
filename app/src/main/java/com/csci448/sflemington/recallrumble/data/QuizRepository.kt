package com.csci448.sflemington.recallrumble.data

object QuizRepository {
    val quiz1 = Quiz(
        title = "My Quiz",
        questionList = listOf(
            QuestionRepository.moviesList[0]
        ),
        category = CategoryRepository.categoryList[5]
    )

    val newQuiz = Quiz(
        title = "New Quiz",
        questionList = listOf(
            Question(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0),
            Question(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0),
            Question(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0),
            Question(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0),
            Question(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0)
        ),
        category = CategoryRepository.categoryList[5]
    )
}