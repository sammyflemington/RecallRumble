package com.csci448.sflemington.recallrumble.data

import androidx.compose.runtime.mutableStateOf
import java.util.UUID

class Quiz(val title: String = "", val questionList: List<Question> = listOf(), val category: Category = Category(0, 0, listOf()),
           val id : String = UUID.randomUUID().toString(), val creatorID: String = "none", val plays : Int = 0) {
    //Hard coding 10 for Alpha Release
    //val quizQuestionCount = questionList.size
    val quizQuestionCount = 5

    fun toMutableQuiz(): MutableQuiz{
        val mutableQuestionList : MutableList<MutableQuestion> = mutableListOf()
        questionList.forEach{question->
            mutableQuestionList.add(question.toMutableQuestion())
        }
        return MutableQuiz(title = mutableStateOf(title), questionList = mutableQuestionList.toList(), category = mutableStateOf(category), creatorID = mutableStateOf(creatorID), id = id, plays = plays)
    }
}