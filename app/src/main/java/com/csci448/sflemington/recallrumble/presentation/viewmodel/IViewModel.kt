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

    val currentViewedUser : User?

    val currentQuizCreating : Quiz?
    fun onUserProfileSaved (name: String, username: String)
    fun newQuizPlay(quizPlay: QuizPlay)
    fun setViewedUser(user: User)

}