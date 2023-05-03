package com.csci448.sflemington.recallrumble.data

import androidx.compose.runtime.MutableState
import com.google.gson.Gson
import java.util.*

class MutableQuiz (val title: MutableState<String>, val questionList: List<MutableQuestion>, val category: MutableState<Category>, val id : UUID = UUID.randomUUID(), val creatorID : MutableState<String>) {

    val quizQuestionCount = 5
    fun toQuiz() : Quiz{
        var questions : MutableList<Question> = mutableListOf()
        questionList.forEach{it->
            questions.add(it.toQuestion())
        }
        return Quiz(title.value, questions.toList(), category.value, id, creatorID.value)
    }

    fun deepCopy(): MutableQuiz {
        val JSON = Gson().toJson(this)
        return Gson().fromJson(JSON, MutableQuiz::class.java)
    }
}