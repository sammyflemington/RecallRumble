package com.csci448.sflemington.recallrumble.data.user

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector

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

    val profilePictures : List<ImageVector> = listOf(

    )
    init {
        Log.d(LOG_TAG, "initializing repository list")
        user = User(
            name = "NoName",
            username = "NoUsername",
            following = listOf("7Mrdw94PDZeQw39qKMm0WQo66Dt1"),
            rank = 999,
            gamesPlayed = 0,
            uid = "d")
        leaderBoard = listOf()
    }

}