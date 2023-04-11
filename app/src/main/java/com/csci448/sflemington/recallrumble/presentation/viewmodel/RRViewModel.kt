package com.csci448.sflemington.recallrumble.presentation.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.csci448.sflemington.recallrumble.data.*
import com.csci448.sflemington.recallrumble.data.user.RRRepo
import com.csci448.sflemington.recallrumble.data.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class RRViewModel(user: User, leaderBoard: List<User>) : ViewModel(), IViewModel {
    companion object {
        private const val LOG_TAG = "448.RRViewModel"
    }
    private val mUser = mutableStateOf(user)
    // for firebase access to save user data
    private val userDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")

    private val mLeaderboard = leaderBoard.toMutableStateList()

    private val mCategoryList = CategoryRepository.categoryList

    override val user: User
        get() = mUser.value

    override val leaderBoard: List<User>
        get() = mLeaderboard.toList().sortedBy { it.rank }

    override val categoryList: List<Category>
        get() = mCategoryList

    private val mCurrentQuestionIndex: MutableState<Int> = mutableStateOf(0)

    override val currentQuestionNumber: Int
        get() = mCurrentQuestionIndex.value + 1

    private val mCurrentQuestion: MutableState<Question?> = mutableStateOf(null)

    override val currentQuestionState: Question?
        get() = mCurrentQuestion.value

    //Add to IViewModel?
    private val mCurrentGame: MutableState<QuizPlay?> = mutableStateOf(null)

    override val currentGame: QuizPlay?
        get() = mCurrentGame.value

    private val mName = mutableStateOf(user.name)
    private val mUserName = mutableStateOf(user.username)

    // viewing other user profiles
    private val mCurrentViewedUser: MutableState<User?> = mutableStateOf(null)
    override val currentViewedUser: User?
        get() = mCurrentViewedUser.value

    init {
        val auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid.toString()

        if (uid.isNotEmpty()){
            getUserData(uid)
        }
    }
    private fun getUserData(uid: String) {
        userDatabaseReference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userData : User? = snapshot.getValue(User::class.java)
                if (userData != null) {
                    mUser.value = userData
                    Log.d(LOG_TAG, "User data successfully retrieved")
                }else{
                    Log.d(LOG_TAG,"Failed to retrieve user data!")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled if needed
            }
        })
    }
    override fun setViewedUser(user: User) {
        mCurrentViewedUser.value = user
    }

    override fun onUserProfileSaved(name: String, username: String) {
        Log.d(LOG_TAG, "onUserProfileSaved called")
        val auth : FirebaseAuth = FirebaseAuth.getInstance()
        val uid : String? = auth.currentUser?.uid

        mName.value = name
        mUserName.value = username
        mUser.value = User(
            name = mName.value,
            username = mUserName.value,
            user.friends,
            user.rank,
            user.gamesWon,
            user.gamesLost,
            uid = uid
        )

        if (uid != null){
            userDatabaseReference.child(uid).setValue(mUser.value).addOnCompleteListener{
                if (it.isSuccessful){
                    Log.d(LOG_TAG, "Profile updated successfully!")
                    //Toast.makeText(this.c, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
                }else{
                    Log.d(LOG_TAG, "Profile failed to update.")
                    //Toast.makeText(currentCompositionLocalContext, "Failed to update profile", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun newQuizPlay(quizPlay: QuizPlay) {

        mCurrentGame.value = quizPlay
        mCurrentQuestion.value = quizPlay.quiz.questionList[mCurrentQuestionIndex.value]
        //mCurrentQuizQuestionCount.value = quizPlay.quiz.quizQuestionCount

    }
}
