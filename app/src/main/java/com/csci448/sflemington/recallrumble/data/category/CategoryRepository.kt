package com.csci448.sflemington.recallrumble.data

import androidx.compose.ui.res.stringResource
import com.csci448.sflemington.recallrumble.R
//import com.csci448.sflemington.recallrumble.data.category.Category

object CategoryRepository {
    val categoryList = listOf<Category>(
        Category(R.string.category_geography, R.drawable.geography_graphic),
        Category(R.string.category_history, R.drawable.geography_graphic),
        Category(R.string.category_popculture, R.drawable.geography_graphic),
        Category(R.string.category_science, R.drawable.geography_graphic),
        Category(R.string.category_sports, R.drawable.geography_graphic),
        Category(R.string.category_movies, R.drawable.geography_graphic)
    )
}