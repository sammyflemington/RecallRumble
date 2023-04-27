package com.csci448.sflemington.recallrumble.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList

class MutableQuestion (override val prompt : String, override val answerChoices : List<String>, override val correctAnswerIndex : Int ) : IQuestion {
    val mPrompt : MutableState<String> = mutableStateOf(prompt)
    val mAnswerChoices : MutableList<String> = answerChoices.toMutableStateList()
    val mCorrectAnswerIndex : MutableState<Int> = mutableStateOf(correctAnswerIndex)

    fun toQuestion() : Question{
        return Question(mPrompt.value, mAnswerChoices.toList(), mCorrectAnswerIndex.value)
    }
}