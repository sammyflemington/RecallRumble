package com.csci448.sflemington.recallrumble.data

import androidx.compose.ui.res.stringResource
import com.csci448.sflemington.recallrumble.R

object QuestionRepository {
    val geographyList = listOf<Question>(
        Question(R.string.geography_q1, listOf(R.string.geo_q1_choice1, R.string.geo_q1_choice2, R.string.geo_q1_choice3, R.string.geo_q1_choice4), 2)
    )
    val historyList = listOf<Question>(
        Question(R.string.history_q1, listOf(R.string.hist_q1_choice1, R.string.hist_q1_choice2, R.string.hist_q1_choice3, R.string.hist_q1_choice4), 0)
    )
    val popCultureList = listOf<Question>(
        Question(R.string.popculture_q1, listOf(R.string.pop_q1_choice1, R.string.pop_q1_choice2, R.string.pop_q1_choice3, R.string.pop_q1_choice4), 1)
    )
    val scienceList = listOf<Question>(
        Question(R.string.science_q1, listOf(R.string.sci_q1_choice1, R.string.sci_q1_choice2, R.string.sci_q1_choice3, R.string.sci_q1_choice4), 2)
    )
    val sportsList = listOf<Question>(
        Question(R.string.sport_q1, listOf(R.string.sport_q1_choice1, R.string.sport_q1_choice2, R.string.sport_q1_choice3, R.string.sport_q1_choice4), 3)
    )
    val moviesList = listOf<Question>(
        Question(R.string.movie_q1, listOf(R.string.movie_q1_choice1, R.string.movie_q1_choice2, R.string.movie_q1_choice3, R.string.movie_q1_choice4), 1)
    )

}