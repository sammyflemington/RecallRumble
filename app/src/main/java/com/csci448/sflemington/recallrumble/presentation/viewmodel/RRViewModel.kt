package com.csci448.sflemington.recallrumble.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.csci448.sflemington.recallrumble.data.*
import com.csci448.sflemington.recallrumble.data.user.RRRepo
import com.csci448.sflemington.recallrumble.data.user.User

class RRViewModel(user: User, leaderBoard: List<User>) : ViewModel(), IViewModel{
    companion object {
        private const val LOG_TAG = "448.RRViewModel"
    }

    private val mUser = mutableStateOf(user)

    private val mLeaderboard = leaderBoard.toMutableStateList()

    private val mCategoryList = CategoryRepository.categoryList

    override val user: User
        get() = mUser.value

    override val leaderBoard: List<User>
        get() = mLeaderboard.toList().sortedBy { it.rank }

    override val categoryList: List<Category>
        get() = mCategoryList

    private val mCurrentQuestionIndex : MutableState<Int> = mutableStateOf(0)

    override val currentQuestionNumber : Int
        get() = mCurrentQuestionIndex.value + 1

    private val mCurrentQuestion : MutableState<Question?> = mutableStateOf(null)

    override val currentQuestionState : Question?
        get() = mCurrentQuestion.value

    //Add to IViewModel?
    private val mCurrentGame : MutableState<QuizPlay?> = mutableStateOf(null)

    override val currentGame: QuizPlay?
        get() = mCurrentGame.value

    private val mName = mutableStateOf(user.name)
    private val mUserName = mutableStateOf(user.username)

    override fun onUserProfileSaved(name : String, username : String){
        Log.d(LOG_TAG, "onUserProfileSaved called")
        mName.value = name
        mUserName.value = username
        mUser.value = User(name = mName.value, username = mUserName.value, user.friends, user.rank, user.gamesWon, user.gamesLost)
    }
    override fun newQuizPlay(quizPlay: QuizPlay) {
        mCurrentGame.value = quizPlay
        mCurrentQuestion.value = quizPlay.quiz.questionList[mCurrentQuestionIndex.value]
        //mCurrentQuizQuestionCount.value = quizPlay.quiz.quizQuestionCount
    }

}

