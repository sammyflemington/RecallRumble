package com.csci448.sflemington.recallrumble.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csci448.sflemington.recallrumble.data.user.RRRepo
import com.csci448.sflemington.recallrumble.data.user.User

class RRViewModelFactory(private val context: Context): ViewModelProvider.NewInstanceFactory() {
    companion object {
        private const val LOG_TAG = "448.RRViewModelFactory"
    }

    fun getViewModelClass() = RRViewModel::class.java

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        Log.d(LOG_TAG, "create() called")
        if(modelClass.isAssignableFrom(getViewModelClass())) {
            Log.d(LOG_TAG, "creating ViewModel: ${getViewModelClass()}")
            return modelClass
                .getConstructor(User::class.java,List::class.java)
                .newInstance(RRRepo.getInstance(context).user, RRRepo.getInstance(context).leaderBoard)
        }
        Log.e(LOG_TAG, "Unknown ViewModel: $modelClass")
        throw IllegalArgumentException("Unknown ViewModel")
    }
}