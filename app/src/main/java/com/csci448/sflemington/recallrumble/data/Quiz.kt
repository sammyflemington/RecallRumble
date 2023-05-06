package com.csci448.sflemington.recallrumble.data

import java.util.UUID

class Quiz(val title: String = "", val questionList: List<Question> = listOf(), val category: Category = Category(0, 0, listOf()),
           val id : String = UUID.randomUUID().toString(), val creatorID: String = "none") {
    //Hard coding 10 for Alpha Release
    //val quizQuestionCount = questionList.size
    val quizQuestionCount = 5
}