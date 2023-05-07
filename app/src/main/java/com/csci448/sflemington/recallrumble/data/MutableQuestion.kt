package com.csci448.sflemington.recallrumble.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList

class MutableQuestion (override val prompt : String, override val answerChoices : List<String>, override val correctAnswerIndex : Int ) : IQuestion {
    val mPrompt : MutableState<String> = mutableStateOf(prompt)
    val mAnswerChoices : List<MutableState<String>> = listOf(
        mutableStateOf(answerChoices[0]),
        mutableStateOf(answerChoices[1]),
        mutableStateOf(answerChoices[2]),
        mutableStateOf(answerChoices[3]),
    )
    val mCorrectAnswerIndex : MutableState<Int> = mutableStateOf(correctAnswerIndex)

    fun toQuestion() : Question{
        val answers = listOf(
            mAnswerChoices[0].value,
            mAnswerChoices[1].value,
            mAnswerChoices[2].value,
            mAnswerChoices[3].value,
        )
        return Question(mPrompt.value, answers, mCorrectAnswerIndex.value)
    }

    fun hasEmptyField(): Boolean{
        if (mPrompt.value.isEmpty()){
            return true
        }
        mAnswerChoices.forEach{choice ->
            if (choice.value.isEmpty()){
                return true
            }
        }
        return false
    }
}