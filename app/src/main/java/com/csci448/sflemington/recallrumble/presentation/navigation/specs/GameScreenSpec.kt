package com.csci448.sflemington.recallrumble.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.presentation.screens.CategoryListScreen
import com.csci448.sflemington.recallrumble.presentation.screens.GameScreen
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel

object GameScreenSpec : IScreenSpec {
    override val route = "gameScreen"
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
        GameScreen(
            viewModel,
            onCorrect = {
                viewModel.onCorrectAnswer()
                if (viewModel.currentQuestionNumber >= 6) {
                    viewModel.updateGameResults()
                    navController.navigate(GameResultsScreenSpec.buildRoute())
                }
                        } ,
            onWrong = {
                viewModel.onWrongAnswer()
                if (viewModel.currentQuestionNumber >= 6) {
                    viewModel.updateGameResults()
                    navController.navigate(GameResultsScreenSpec.buildRoute())
                }
            })
    }

    @Composable
    override fun TopAppBarActions(viewModel: IViewModel, navController: NavHostController, navBackStackEntry: NavBackStackEntry?, context: Context){

    }
}