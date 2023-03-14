package com.csci448.sflemington.recallrumble.presentation.navigation.specs

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.presentation.screens.QuizCreationLandingPage
import com.csci448.sflemington.recallrumble.presentation.screens.QuizCreationScreen
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel

object QuizCreationLandingPageSpec : IScreenSpec {
    override val route = "quizCreationLanding"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args : String?) = route
    override val title : Int = R.string.app_name
    override val icon = Icons.Filled.Create
    @Composable
    override fun Content(
        viewModel: IViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
        val context = LocalContext.current
        QuizCreationLandingPage(onCreateQuizClicked ={
             navController.navigate(QuizCreationScreenSpec.buildRoute())
        }, onViewQuizzesClicked = {
            Toast.makeText(context, "You will be directed to a list of your quizzes", Toast.LENGTH_SHORT).show()
        })
        }


    @Composable
    override fun TopAppBarActions(viewModel: IViewModel, navController: NavHostController, navBackStackEntry: NavBackStackEntry?, context: Context){

    }
}