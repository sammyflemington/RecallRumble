package com.csci448.sflemington.recallrumble.data

class Quiz(val title: String, val questionList: List<Question>, category: Category) {

    val quizQuestionCount = questionList.size

}