package com.csci448.sflemington.recallrumble.presentation.viewmodel

import com.csci448.sflemington.recallrumble.data.user.User
import kotlinx.coroutines.flow.StateFlow

interface IViewModel {
    val user: User
    val leaderBoard: List<User>
    fun onUserProfileSaved (name: String, username: String)
}