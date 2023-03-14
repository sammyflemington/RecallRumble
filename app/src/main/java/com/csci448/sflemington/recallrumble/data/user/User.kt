package com.csci448.sflemington.recallrumble.data.user

import java.util.*

data class User(
    val name: String,
    val username: String,
    val friends: List<User>?,
    val rank: Int?,
    val gamesWon: Int,
    val gamesLost: Int,
    val id : UUID = UUID.randomUUID()
)
