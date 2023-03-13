package com.csci448.sflemington.recallrumble.data

class Quiz(val title: String, val questionList: List<Question>, val category: Category) {

    val quizQuestionCount = questionList.size

}