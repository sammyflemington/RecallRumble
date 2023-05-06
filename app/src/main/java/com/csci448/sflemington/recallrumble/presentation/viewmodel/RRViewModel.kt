package com.csci448.sflemington.recallrumble.presentation.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.csci448.sflemington.recallrumble.data.*
import com.csci448.sflemington.recallrumble.data.user.RRRepo
import com.csci448.sflemington.recallrumble.data.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlin.random.Random

class RRViewModel(user: User, leaderBoard: List<User>) : ViewModel(), IViewModel {
    companion object {
        private const val LOG_TAG = "448.RRViewModel"
    }
    private val mUser = mutableStateOf(user)
    // for firebase access to save user data
    private val userDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")

    private val quizDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Quizzes")

    private var mLeaderboard = leaderBoard.toMutableStateList()

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

    // creating a quiz
    private val mCurrentQuizCreating : MutableState<MutableQuiz> = mutableStateOf(QuizRepository.newQuiz)
    override val currentQuizCreating : MutableQuiz
        get() = mCurrentQuizCreating.value

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

    override fun saveQuiz(){
        Log.d(LOG_TAG, "Saving quiz id " + mCurrentQuizCreating.value.id.toString())
        val auth : FirebaseAuth = FirebaseAuth.getInstance()
        val id : String? = mCurrentQuizCreating.value.id.toString()
        if (id != null){
            val quizToSave : Quiz
            quizDatabaseReference.child(id).setValue(mCurrentQuizCreating.value.toQuiz()).addOnCompleteListener{
                if (it.isSuccessful){
                    Log.d(LOG_TAG, "Quiz uploaded successfully!")
                    //Toast.makeText(this.c, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
                }else{
                    Log.d(LOG_TAG, "Quiz failed to upload.")
                    //Toast.makeText(currentCompositionLocalContext, "Failed to update profile", Toast.LENGTH_SHORT).show()
                }
            }
        }

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

    override fun fetchQuizFromCategory(category: Int) {
        Log.d(LOG_TAG, "Loading quiz from category " + category.toString())
        quizDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val t = object: GenericTypeIndicator<HashMap<String, Quiz>?> (){}
                Log.d(LOG_TAG, "RAW DATA: " + snapshot.value)
                val allQuizzes : HashMap<String, Quiz>? = snapshot.getValue(t)
                Log.d(LOG_TAG, "Quizzes fetched : " + allQuizzes)
                if (allQuizzes != null) {
                    val quizzesInCategory : MutableList<Quiz> = mutableListOf()
                    for (quiz : Quiz in allQuizzes.values!!){
                        if (quiz.category.category == category){
                            quizzesInCategory.add(quiz)
                        }
                    }
                    val quiz : Quiz = quizzesInCategory[Random.nextInt(0, quizzesInCategory.size)]
                    mCurrentGame.value = QuizPlay(quiz, user, 0)
                    mCurrentQuestion.value = mCurrentGame.value?.quiz!!.questionList[mCurrentQuestionIndex.value]
                }else{
                    Log.d(LOG_TAG,"Failed to retrieve quiz data!")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled if needed
            }
        })
//        Log.d(LOG_TAG, "Loading quiz from category " + category.toString())
//        val auth : FirebaseAuth = FirebaseAuth.getInstance()
//        quizDatabaseReference.get().addOnCompleteListener{
//            if (it.isSuccessful){
//                val allQuizzes = it.result as HashMap<String, HashMap<String, Quiz>>
//                Log.d(LOG_TAG, "Quizzes fetched : " + allQuizzes)
//                // choose a random quiz from the category
////                val quizzesInCategory : MutableList<Quiz> = mutableListOf()
////                for (quiz : Quiz in allQuizzes.values){
////                    if (quiz.category.category == category){
////                        quizzesInCategory.add(quiz)
////                    }
////                }
////                val quiz : Quiz = quizzesInCategory[Random.nextInt(0, quizzesInCategory.size)]
////                mCurrentGame.value = QuizPlay(quiz, user, 0)
//                //Toast.makeText(this.c, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
//            }else{
//                Log.d(LOG_TAG, "Failed to fetch quiz.")
//                //Toast.makeText(currentCompositionLocalContext, "Failed to update profile", Toast.LENGTH_SHORT).show()
//            }
//        }

    }
    override fun createNewQuiz() {
        mCurrentQuizCreating.value = MutableQuiz(
            title = mutableStateOf("New Quiz"),
            questionList = listOf(
                MutableQuestion(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0),
                MutableQuestion(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0),
                MutableQuestion(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0),
                MutableQuestion(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0),
                MutableQuestion(prompt = "", answerChoices = listOf("","","",""), correctAnswerIndex = 0)
            ),
            category = mutableStateOf(CategoryRepository.categoryList[5]),
            creatorID = mutableStateOf("")
        )
        mCurrentQuizCreating.value.creatorID.value = user.uid.toString()
    }

    override fun onCorrectAnswer(){
        if (mCurrentGame.value != null){
            mCurrentGame.value!!.player1Score += 1
        }
       nextQuestion()
    }
    override fun onWrongAnswer() {
        nextQuestion()
    }
    fun nextQuestion(){
        mCurrentQuestionIndex.value += 1
        if (mCurrentGame.value?.quiz != null){
            mCurrentQuestion.value = mCurrentGame.value?.quiz!!.questionList[mCurrentQuestionIndex.value]
        }

    }
    override fun updateLeaderboard() {
//        userDatabaseReference.get().addOnSuccessListener { userList -> // List<Question>
//            val tempList: MutableList<User> = mutableListOf()
//            val t : GenericTypeIndicator<HashMap<String, Object>> = GenericTypeIndicator()
//            val map  = userList.getValue(t)
//            if (map != null){
//                for (item in map.values){
//                    tempList.add(item)
//                }
//                mLeaderboard = tempList.toMutableStateList()
//            }
//
//            //Log.e(LOG_TAG, "Values: "+ map::class)
//
//
////            val map = userList.getValue() as Map<*, *>
////            Log.e(LOG_TAG, "Values: "+ map.get("MdKM1UHBleTH4xtwAr0i4O9Hiry1")!!::class)
////            //mLeaderboard = map.values.toList().toMutableStateList() as SnapshotStateList<User>
//        }.addOnFailureListener {
//            Log.e(LOG_TAG, "Error", it)
//        }
    }
}
