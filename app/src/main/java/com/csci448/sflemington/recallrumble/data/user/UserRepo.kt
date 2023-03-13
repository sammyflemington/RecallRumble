package com.csci448.sflemington.recallrumble.data.user

import android.content.Context
import android.util.Log

class UserRepo (context: Context?){
    companion object {
        private const val LOG_TAG = "448.UserRepo"
        private var INSTANCE: UserRepo? = null

        /**
         * @param context
         */
        fun getInstance(context: Context? = null): UserRepo {
            var instance = INSTANCE
            if(instance == null) {
                instance = UserRepo(context)
                INSTANCE = instance
            }
            return instance
        }
    }

    val characters: List<User>

    init {
        Log.d(LOG_TAG, "initializing repository list")
        val characterList = mutableListOf<User>()

        characters = characterList.toList()
    }

}