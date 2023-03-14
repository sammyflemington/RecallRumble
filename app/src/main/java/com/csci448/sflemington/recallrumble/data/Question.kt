package com.csci448.sflemington.recallrumble.data

import com.csci448.sflemington.recallrumble.R

class Question(val prompt : Int = R.string.default_question_prompt, val answerChoices : List<Int>, val correctAnswerIndex : Int = 0) {
}