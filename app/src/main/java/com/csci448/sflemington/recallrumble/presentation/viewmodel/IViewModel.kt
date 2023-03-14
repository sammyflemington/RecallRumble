package com.csci448.sflemington.recallrumble.presentation.viewmodel

import com.csci448.sflemington.recallrumble.data.*
import com.csci448.sflemington.recallrumble.data.user.User
import kotlinx.coroutines.flow.StateFlow

interface IViewModel {
    val user: User
    val leaderBoard: List<User>
    val categoryList: List<Category>

    val currentGame: QuizPlay?
    val currentQuestionNumber : Int
    val currentQuestionState : Question?
    //val currentQuestionStatus : QuestionStatus?
    val currentViewedUser : User?
    // Both question index and question count can be changed when Quiz class is implemented
    // to hold a current quiz state
    //val currentQuestionIndex: Int
    //val currentQuizQuestionCount: Int
    fun onUserProfileSaved (name: String, username: String)
    fun newQuizPlay(quizPlay: QuizPlay)
    //fun updateStatus(correct: Boolean)
    fun setViewedUser(user: User)

}