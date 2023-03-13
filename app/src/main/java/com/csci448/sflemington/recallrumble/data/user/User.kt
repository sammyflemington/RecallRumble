package com.csci448.sflemington.recallrumble.data.user

data class User(
    val name: String,
    val username: String,
    val friends: List<User>?,
    val rank: Int?,
    val gamesWon: Int,
    val gamesLost: Int)
