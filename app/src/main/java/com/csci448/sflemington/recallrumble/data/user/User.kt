package com.csci448.sflemington.recallrumble.data.user

import java.util.*

data class User(
    val name: String = "",
    val username: String = "",
    val friends: List<User>? = null,
    val rank: Int?= null,
    val gamesWon: Int = 0,
    val gamesLost: Int = 0,
    val profilePictureId : Int = 0,
    //val id : UUID = UUID.randomUUID(), // local one
    val uid : String? = null // database one
)
