package com.csci448.sflemington.recallrumble.presentation.viewmodel

import com.csci448.sflemington.recallrumble.data.Category
import com.csci448.sflemington.recallrumble.data.CategoryRepository
import com.csci448.sflemington.recallrumble.data.user.User
import kotlinx.coroutines.flow.StateFlow

interface IViewModel {
    val user: User
    val leaderBoard: List<User>
    val categoryList: List<Category>
    // Both question index and question count can be changed when Quiz class is implemented
    // to hold a current quiz state
    //val currentQuestionIndex: Int
    //val currentQuizQuestionCount: Int
    fun onUserProfileSaved (name: String, username: String)
}