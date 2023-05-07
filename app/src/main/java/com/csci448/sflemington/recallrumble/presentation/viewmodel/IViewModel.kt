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

    val currentScore: Int

    val currentViewedUser : User?

    val currentQuizCreating : MutableQuiz

    val currentGameCreator: String

    fun onUserProfileSaved (name: String, username: String)
    fun newQuizPlay(quizPlay: QuizPlay)
    fun setViewedUser(user: User)
    fun saveQuiz()
    fun createNewQuiz()
    fun updateLeaderboard()

    fun onCorrectAnswer()
    fun onWrongAnswer()
    fun fetchQuizFromCategory(category: Int)
}