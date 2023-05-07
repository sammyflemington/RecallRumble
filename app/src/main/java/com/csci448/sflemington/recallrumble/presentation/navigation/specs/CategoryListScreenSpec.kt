package com.csci448.sflemington.recallrumble.presentation.navigation.specs

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.data.QuizPlay
import com.csci448.sflemington.recallrumble.presentation.screens.CategoryListScreen
import com.csci448.sflemington.recallrumble.presentation.screens.CreateAccountScreen
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel

object CategoryListScreenSpec : IScreenSpec {
    override val route = "categoryList"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args : String?) = route
    override val title : Int = R.string.app_name
    override val icon = null
    @Composable
    override fun Content(
        viewModel: IViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
        CategoryListScreen(viewModel.categoryList, {

            if (viewModel.currentGame != null){
                viewModel.newQuizPlay(QuizPlay(viewModel.currentGame!!.quiz, viewModel.user, 0))
            }
            viewModel.fetchQuizFromCategory(it)


            navController.navigate("gameScreen")

                                                   }, view = viewModel)
    }

    @Composable
    override fun TopAppBarActions(viewModel: IViewModel, navController: NavHostController, navBackStackEntry: NavBackStackEntry?, context: Context){

    }
}