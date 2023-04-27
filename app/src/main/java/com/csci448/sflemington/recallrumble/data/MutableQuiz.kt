package com.csci448.sflemington.recallrumble.data

import androidx.compose.runtime.MutableState
import java.util.*

class MutableQuiz (val title: MutableState<String>, val questionList: List<MutableQuestion>, val category: MutableState<Category>, val id : UUID = UUID.randomUUID()) {

    val quizQuestionCount = 5
    fun toQuiz() : Quiz{
        var questions : MutableList<Question> = mutableListOf()
        questionList.forEach{it->
            questions.add(it.toQuestion())
        }
        return Quiz(title.value, questions.toList(), category.value)
    }
}