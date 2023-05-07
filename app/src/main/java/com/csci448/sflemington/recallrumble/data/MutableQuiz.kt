package com.csci448.sflemington.recallrumble.data

import androidx.compose.runtime.MutableState
import java.util.*

class MutableQuiz (val title: MutableState<String>, val questionList: List<MutableQuestion>, val category: MutableState<Category>, val id : String = UUID.randomUUID().toString(), val creatorID : MutableState<String>, val plays : Int = 0) {

    val quizQuestionCount = 5
    fun toQuiz() : Quiz{
        var questions : MutableList<Question> = mutableListOf()
        questionList.forEach{it->
            questions.add(it.toQuestion())
        }
        return Quiz(title.value, questions.toList(), category.value, id, creatorID.value, plays)
    }

    fun hasEmptySlot(): Boolean {
        questionList.forEach{question->
            if (question.hasEmptyField()){
                return true
            }
        }
        if (title.value == ""){
            return true
        }
        return false
    }
//    fun deepCopy(): MutableQuiz {
//        val JSON = Gson().toJson(this)
//        return Gson().fromJson(JSON, MutableQuiz::class.java)
//    }
}