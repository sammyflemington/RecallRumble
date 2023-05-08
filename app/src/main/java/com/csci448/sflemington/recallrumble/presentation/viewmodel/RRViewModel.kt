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
import java.lang.Math.round
import kotlin.math.roundToInt
import kotlin.random.Random

class RRViewModel(user: User, leaderboard: List<User>) : ViewModel(), IViewModel {
    companion object {
        private const val LOG_TAG = "448.RRViewModel"
    }
    private val mUser = mutableStateOf(user)
    // for firebase access to save user data
    private val userDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")

    private val quizDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Quizzes")

    private var mLeaderboard : SnapshotStateList<User> = listOf(mUser.value).toMutableStateList()

    private val mCategoryList = CategoryRepository.categoryList
    private val mNewBestScore: MutableState<Boolean> = mutableStateOf(false)
    override val newBestScore : Boolean
        get() = mNewBestScore.value
    override val user: User
        get() = mUser.value

    override val leaderBoard: List<User>
        get() = mLeaderboard.toList().sortedBy { -it.bestScoresByCategory.values.sum() ?: 0 }

    private val mUserFollowing : MutableList<User> = mutableListOf()
    override val userFollowing : List<User>
        get() = mUserFollowing.toList()

    private val mIsViewedUserFollowed : MutableState<Boolean> = mutableStateOf(false)
    override val isViewedUserFollowed: Boolean
        get() = mIsViewedUserFollowed.value
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

    private val mCurrentUserQuizzes : MutableList<Quiz> = mutableListOf()
    override val currentUserQuizzes: List<Quiz>
        get() = mCurrentUserQuizzes.toList()

    private val mCurrentScore : MutableState<Int> = mutableStateOf(0)

    override val currentScore: Int
        get() = mCurrentScore.value

    val isAnimated: Boolean
        get() = mIsAnimated.value

    private val mIsAnimated : MutableState<Boolean> = mutableStateOf(false)

    override val currentGameCreator: String
        get() = mCurrentGameCreator.value

    private val mCurrentGameCreator : MutableState<String> = mutableStateOf("")



    init {
        val auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid.toString()

        Log.d(LOG_TAG, "UID: " + uid)

        if (uid.isNotEmpty()){
            getUserData(uid)
        }
    }

    private fun getUserData(uid: String) {
        userDatabaseReference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userData : User? = snapshot.getValue(User::class.java)
                Log.d(LOG_TAG, "User :" + userData)
                if (userData != null) {
                    mUser.value = userData
                    Log.d(LOG_TAG, "User data successfully retrieved. Name:" + userData.name)
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
        if (user in userFollowing){
            mIsViewedUserFollowed.value = true
        }else{
            mIsViewedUserFollowed.value = false
        }
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
    override fun loadQuizToEdit(quiz: Quiz){
        mCurrentQuizCreating.value = quiz.toMutableQuiz()
    }
    override fun fetchCurrentUserQuizzes() {
        Log.d(LOG_TAG, "Loading user's created quizzes ")
        quizDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val t = object: GenericTypeIndicator<HashMap<String, Quiz>?> (){}
                val allQuizzes : HashMap<String, Quiz>? = snapshot.getValue(t)
                Log.d(LOG_TAG, "Quizzes fetched!")
                if (allQuizzes != null) {
                    val quizzesByUser : MutableList<Quiz> = mutableListOf()
                    for (quiz : Quiz in allQuizzes.values!!){
                        if (quiz.creatorID == user.uid){
                            quizzesByUser.add(quiz)
                        }
                    }
                    mCurrentUserQuizzes.clear()
                    mCurrentUserQuizzes.addAll(quizzesByUser.toList())
                }else{
                    Log.d(LOG_TAG,"Failed to retrieve user's quiz data!")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled if needed
            }
        })
    }
    override fun followUser(id: String){
        Log.d(LOG_TAG, "followUser called")
        val auth : FirebaseAuth = FirebaseAuth.getInstance()
        val uid : String? = auth.currentUser?.uid
        val following : MutableList<String> = user.following.toMutableList()
        if (id !in following){
            following.add(id)
            mIsViewedUserFollowed.value = true
        }else{
            following.remove(id)
            mIsViewedUserFollowed.value = false
        }
        mUser.value = User(
            name = mUser.value.name,
            username = mUser.value.username,
            following,
            user.rank,
            user.gamesPlayed,
            uid = uid,
            bestScoresByCategory = mUser.value.bestScoresByCategory
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
    //updateLeaderboard()
    }
    override fun onUserProfileSaved(name: String, username: String) {
        Log.d(LOG_TAG, "onUserProfileSaved called")
        val auth : FirebaseAuth = FirebaseAuth.getInstance()
        val uid : String? = auth.currentUser?.uid

        mName.value = name
        mUserName.value = username
        mUser.value = User(
            name = name,
            username = username,
            user.following,
            user.rank,
            user.gamesPlayed,
            uid = uid,
            bestScoresByCategory = user.bestScoresByCategory
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
        mCurrentViewedUser.value = null
    }

    override fun newQuizPlay(quizPlay: QuizPlay) {
        mCurrentQuestionIndex.value = 0
        mCurrentGame.value = quizPlay
        mCurrentQuestion.value = quizPlay.quiz.questionList[mCurrentQuestionIndex.value]
        mCurrentScore.value = 0


        quizDatabaseReference.child(mCurrentGame.value!!.quiz.creatorID).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userData : User? = snapshot.getValue(User::class.java)
                if (userData != null) {
                    mCurrentGameCreator.value = userData.username
                    Log.d(LOG_TAG, "User data successfully retrieved")
                }else{
                    Log.d(LOG_TAG,"Failed to retrieve user data!")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled if needed
            }
        })

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
                    mCurrentQuestion.value = mCurrentGame.value?.quiz!!.questionList[0]
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
    override fun updateGameResults(){
        Log.d(LOG_TAG, "onUserProfileSaved called")
        val auth : FirebaseAuth = FirebaseAuth.getInstance()
        val uid : String? = auth.currentUser?.uid
        val oldBest = user.bestScoresByCategory[mCurrentGame.value?.quiz?.category?.category.toString()]
        mNewBestScore.value = oldBest != null && oldBest < mCurrentScore.value

        user.bestScoresByCategory[mCurrentGame.value?.quiz?.category?.category.toString()] = mCurrentScore.value

        mUser.value = User(
            name = user.name,
            username = user.username,
            user.following,
            user.rank,
            user.gamesPlayed + 1,
            uid = uid,
            bestScoresByCategory = user.bestScoresByCategory
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
        Log.d(LOG_TAG, "Saving quiz id " + mCurrentQuizCreating.value.id.toString())
        if (mCurrentGame.value != null){
            val id : String? = mCurrentGame.value!!.quiz.id.toString()
            if (id != null){
                val q : Quiz = mCurrentGame.value!!.quiz
                val quizToSave : Quiz = Quiz(q.title, q.questionList, q.category, q.id, q.creatorID, q.plays + 1)
                quizDatabaseReference.child(id).setValue(quizToSave).addOnCompleteListener{
                    if (it.isSuccessful){
                        Log.d(LOG_TAG, "Quiz play count updated successfully!")
                        //Toast.makeText(this.c, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
                    }else{
                        Log.d(LOG_TAG, "Quiz play count failed to upload.")
                        //Toast.makeText(currentCompositionLocalContext, "Failed to update profile", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        //mCurrentViewedUser.value = null
    }
    override fun onCorrectAnswer(time : Float){
        if (mCurrentGame.value != null){
            mCurrentGame.value!!.player1Score += 1
        }
        mCurrentScore.value += (1000f * (time)).roundToInt()
        nextQuestion()
    }
    override fun onWrongAnswer() {
        nextQuestion()
    }

    fun nextQuestion(){
        mCurrentQuestionIndex.value += 1
        if (mCurrentQuestionIndex.value < 5) {

            if (mCurrentGame.value?.quiz != null){
                mCurrentQuestion.value = mCurrentGame.value?.quiz!!.questionList[mCurrentQuestionIndex.value]
            }
        }

    }
    override fun updateLeaderboard() {
        Log.d(LOG_TAG, "Fetching leaderboard data...")
        userDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val t = object: GenericTypeIndicator<HashMap<String, User>?> (){}
                val allUsers : HashMap<String, User>? = snapshot.getValue(t)
                Log.d(LOG_TAG, "Leaderboard fetched!")
                if (allUsers != null) {

                    mLeaderboard.clear()
                    mUserFollowing.clear()
                    allUsers.values.toList().forEach{user->
                        mLeaderboard.add(user)
                        if (user.uid in mUser.value.following){
                            mUserFollowing.add(user)
                        }
                    }
                }else{
                    Log.d(LOG_TAG,"Failed to retrieve leaderboard!")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(LOG_TAG, error.toString())
            }
        })
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
