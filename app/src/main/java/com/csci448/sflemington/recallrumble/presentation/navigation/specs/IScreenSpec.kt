package com.csci448.sflemington.recallrumble.presentation.navigation.specs

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel
import com.csci448.sflemington.recallrumble.R

sealed interface IScreenSpec {
    companion object {
        private const val LOG_TAG = "448.IScreenSpec"

        val allScreens = IScreenSpec::class.sealedSubclasses.associate {
            Log.d(LOG_TAG, "allScreens: mapping route \"${it.objectInstance?.route ?: ""}\" to object \"${it.objectInstance}\"")
            it.objectInstance?.route to it.objectInstance
        }
        const val root = "RecallRumble"
        val startDestination = StartGameScreenSpec.route

        @Composable
        fun TopBar(viewModel: IViewModel, navController: NavHostController, navBackStackEntry: NavBackStackEntry?){
            val route = navBackStackEntry?.destination?.route ?: ""
            allScreens[route]?.TopAppBarContent(viewModel, navController, navBackStackEntry)
        }

        @Composable
        fun BottomBar(viewModel : IViewModel, navController: NavHostController, navBackStackEntry: NavBackStackEntry?){
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary
            ){
                bottomNavItems.forEach{ screen->
                    var selected = false
                    if (navBackStackEntry != null){
                        if (screen.buildRoute("uuid") == navBackStackEntry.destination.route){
                            selected = true
                        }
                    }
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.popBackStack()
                            navController.navigate(screen.buildRoute(viewModel.user.uid.toString()))
                            Log.d(LOG_TAG,"setViewedUser() called")
                            viewModel.setViewedUser(viewModel.user)
                            viewModel.updateLeaderboard()
                                  },
                        label = {
                            screen.title
                        },
                        icon = {
                            Icon(
                                imageVector = screen.icon ?: Icons.Default.Warning,
                                contentDescription = stringResource(screen.title),
                                tint = Color(0xFF3C0066)
                            )
                        }
                    )
                }
            }
        }

        private val bottomNavItems : List<IScreenSpec> = listOf(
            LeaderboardScreenSpec,
            QuizCreationLandingPageSpec,
            StartGameScreenSpec,
            ProfileScreenSpec
        )
    }
    val title : Int
    val route: String
    val arguments: List<NamedNavArgument>
    val icon : ImageVector?
    fun buildRoute(vararg args: String?): String


    @Composable
    fun Content(
        viewModel: IViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TopAppBarContent(viewModel: IViewModel, navController: NavHostController, navBackStackEntry: NavBackStackEntry?){
        TopAppBar(
            title = {
                Text(text = stringResource(title))
            },
            actions = {TopAppBarActions(viewModel, navController, navBackStackEntry, context = LocalContext.current)}
        )
    }



    @Composable
    abstract fun TopAppBarActions(viewModel: IViewModel, navController: NavHostController, navBackStackEntry: NavBackStackEntry?, context: Context)

}