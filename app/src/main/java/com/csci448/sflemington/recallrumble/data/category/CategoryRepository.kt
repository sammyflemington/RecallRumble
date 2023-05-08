package com.csci448.sflemington.recallrumble.data

import androidx.compose.ui.res.stringResource
import com.csci448.sflemington.recallrumble.R
//import com.csci448.sflemington.recallrumble.data.category.Category

object CategoryRepository {
    val categoryList = listOf<Category>(
        Category(R.string.category_geography, R.drawable.geography, QuestionRepository.geographyList),
        Category(R.string.category_history, R.drawable.history, QuestionRepository.historyList),
        Category(R.string.category_popculture, R.drawable.popculture, QuestionRepository.popCultureList),
        Category(R.string.category_science, R.drawable.science, QuestionRepository.scienceList),
        Category(R.string.category_sports, R.drawable.sports, QuestionRepository.sportsList),
        Category(R.string.category_movies, R.drawable.movies, QuestionRepository.moviesList)
    )
}