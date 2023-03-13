package com.csci448.sflemington.recallrumble.data.user

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf

class RRRepo (context: Context?){
    companion object {
        private const val LOG_TAG = "448.RRRepo"
        private var INSTANCE: RRRepo? = null

        /**
         * @param context
         */
        fun getInstance(context: Context? = null): RRRepo {
            var instance = INSTANCE
            if(instance == null) {
                instance = RRRepo(context)
                INSTANCE = instance
            }
            return instance
        }
    }

    val user : User
    val leaderBoard : List<User>

    init {
        Log.d(LOG_TAG, "initializing repository list")
        user = User(
            name = "NoName",
            username = "NoUsername",
            friends = listOf(
                User(name = "Sammy", username = "KoolCookiez96", friends = emptyList(), rank = 3, gamesWon = 0, gamesLost = 100),
                User(name = "Iso", username = "PorkChomper33", friends = emptyList(), rank = 2, gamesWon = 66, gamesLost = 4),
                User(name = "Lexi", username = "PaoneFan123", friends = emptyList(), rank = 1, gamesWon = 100, gamesLost = 0)
            ),
            rank = 999,
            gamesWon = 0,
            gamesLost = 0)
        leaderBoard = listOf(
            User(name = "Sammy", username = "KoolCookiez96", friends = emptyList(), rank = 3, gamesWon = 0, gamesLost = 100),
            User(name = "Iso", username = "PorkChomper33", friends = emptyList(), rank = 2, gamesWon = 66, gamesLost = 4),
            User(name = "Lexi", username = "PaoneFan123", friends = emptyList(), rank = 1, gamesWon = 100, gamesLost = 0)
        )
    }

}