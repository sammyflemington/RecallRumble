package com.csci448.sflemington.recallrumble.data

import com.csci448.sflemington.recallrumble.data.user.User

//Quiz should not be null!!! change when not testing
class QuizPlay(val quiz: Quiz, val player1: User, val player2: User, var player1Score: Int = 0, var player2Score: Int = 0) {
}