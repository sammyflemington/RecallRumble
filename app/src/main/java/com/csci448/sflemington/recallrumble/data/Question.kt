package com.csci448.sflemington.recallrumble.data

import com.csci448.sflemington.recallrumble.R

class Question(val prompt : String = "", val answerChoices : List<String> = listOf(), val correctAnswerIndex : Int = 0) {

    fun toMutableQuestion(){

    }
}