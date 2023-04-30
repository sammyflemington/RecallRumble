package com.csci448.sflemington.recallrumble.data

import java.util.UUID

class Quiz(val title: String, val questionList: List<Question>, val category: Category, val id : UUID, val creatorID: String) {
    //Hard coding 10 for Alpha Release
    //val quizQuestionCount = questionList.size
    val quizQuestionCount = 2
}