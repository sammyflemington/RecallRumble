package com.csci448.sflemington.recallrumble.data

class Question(val prompt : String = "Default Question Prompt", val answerChoices : List<String>, val correctAnswerIndex : Int = 0) {
}