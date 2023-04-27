package com.csci448.sflemington.recallrumble.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

interface IQuestion {
    val prompt : String
    val answerChoices : List<String>
    val correctAnswerIndex : Int
}