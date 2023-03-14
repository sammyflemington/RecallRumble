package com.csci448.sflemington.recallrumble.presentation.navigation.specs

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.presentation.screens.CreateAccountScreen
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel

object EditProfileScreenSpec: IScreenSpec {
    override val route = "editProfile"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args : String?) = route
    override val title : Int = R.string.app_name
    override val icon = Icons.Filled.Star

    @Composable
    override fun Content(
        viewModel: IViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
        CreateAccountScreen(viewModel.user, onUserProfileSaved = {name, username ->
            viewModel.onUserProfileSaved(name, username)
            navController.navigate(ProfileScreenSpec.buildRoute(viewModel.user.id.toString()))
        }) { navController.navigateUp() }
    }

    @Composable
    override fun TopAppBarActions(viewModel: IViewModel, navController: NavHostController, navBackStackEntry: NavBackStackEntry?, context: Context){

    }

}