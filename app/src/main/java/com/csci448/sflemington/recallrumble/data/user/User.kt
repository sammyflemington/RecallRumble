package com.csci448.sflemington.recallrumble.data.user

import com.csci448.sflemington.recallrumble.data.Category
import com.csci448.sflemington.recallrumble.data.CategoryRepository
import java.util.*

data class User(
    val name: String = "",
    val username: String = "",
    val friends: List<User>? = null,
    val rank: Int?= null,
    val gamesWon: Int = 0,
    val gamesLost: Int = 0,
    val profilePictureId : Int = 0,
    //val id : UUID = UUID.randomUUID(), // local one
    val uid : String? = null, // database onel
    val bestScoresByCategory : HashMap<String, Int> = hashMapOf(CategoryRepository.categoryList[0].category.toString() to 0,
        CategoryRepository.categoryList[1].category.toString() to 0,
        CategoryRepository.categoryList[2].category.toString() to 0,
        CategoryRepository.categoryList[3].category.toString() to 0,
        CategoryRepository.categoryList[4].category.toString() to 0,
        CategoryRepository.categoryList[5].category.toString() to 0
    )
)
