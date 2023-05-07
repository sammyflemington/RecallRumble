package com.csci448.sflemington.recallrumble.presentation.navigation.specs

import android.content.Context
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.navigation.*
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.presentation.screens.ProfileScreen
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel

object ProfileScreenSpec : IScreenSpec{
    private const val LOG_TAG = "448.ProfileScreenSpec"
    private const val ROUTE_BASE = "profile"
    private const val ARG_UUID_NAME = "uuid"
    override val route = buildFullRoute(ARG_UUID_NAME)
    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(ARG_UUID_NAME){
            type = NavType.StringType
        }
    )

    private fun buildFullRoute(argVal: String) : String {
        var fullRoute = ROUTE_BASE
        if (argVal == ARG_UUID_NAME){
            fullRoute += "/{$argVal}"
            Log.d(LOG_TAG, "Built base route $fullRoute")
        }else {
            fullRoute += "/$argVal"
            Log.d(LOG_TAG, "Built specific route $fullRoute")
        }
        return fullRoute
    }
    override fun buildRoute(vararg args : String?) = buildFullRoute(args[0]?:"0")
    override val title : Int = R.string.app_name
    override val icon = Icons.Filled.Person
    @Composable
    override fun Content(
        viewModel: IViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
        Log.d(LOG_TAG, "CurrentViewUser: " + viewModel.currentViewedUser?.uid)
        Log.d(LOG_TAG, "User: " + viewModel.user.uid)
        ProfileScreen(viewModel, viewModel.user, viewedUser = viewModel.currentViewedUser, isViewedUserFollowed = viewModel.isViewedUserFollowed, onFollowUserClicked = {viewModel.followUser(viewModel.currentViewedUser?.uid.toString())}, onEditProfileClicked = { navController.navigate(EditProfileScreenSpec.route) }, onViewFriendsClicked = { navController.navigate(FriendsScreenSpec.route)})
    }

    @Composable
    override fun TopAppBarActions(viewModel: IViewModel, navController: NavHostController, navBackStackEntry: NavBackStackEntry?, context: Context){

    }
}