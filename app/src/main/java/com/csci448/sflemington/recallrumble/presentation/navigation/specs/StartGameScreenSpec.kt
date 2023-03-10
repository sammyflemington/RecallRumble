package com.csci448.sflemington.recallrumble.presentation.navigation.specs

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.presentation.screens.StartGameScreen
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel

object StartGameScreenSpec : IScreenSpec{
    override val route = "start"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args : String?) = route
    override val title : Int = R.string.app_name
    override val icon = Icons.Filled.PlayArrow
    @Composable
    override fun Content(
        viewModel: IViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
        StartGameScreen(onStartGameClicked = { navController.navigate("categoryList") }, onInviteFriendClicked = {navController.navigate(FriendsScreenSpec.buildRoute())} )
    }

    @Composable
    override fun TopAppBarActions(viewModel: IViewModel, navController: NavHostController, navBackStackEntry: NavBackStackEntry?, context: Context){

    }
}