package com.csci448.sflemington.recallrumble.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.csci448.sflemington.recallrumble.data.user.User

class RRViewModel(user: User, leaderBoard: List<User>) : ViewModel(), IViewModel{
    companion object {
        private const val LOG_TAG = "448.RRViewModel"
    }

    private val mUser = mutableStateOf(user)

    private val mLeaderboard = leaderBoard.toMutableStateList()

    override val user: User
        get() = mUser.value

    override val leaderBoard: List<User>
        get() = mLeaderboard.toList()
}