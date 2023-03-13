package com.csci448.sflemington.recallrumble.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.csci448.sflemington.recallrumble.presentation.navigation.specs.IScreenSpec
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel

@Composable
fun RRNavHost(modifier: Modifier = Modifier,
                      navController: NavHostController,
                      viewModel: IViewModel,
                      context: Context
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = IScreenSpec.root
    ) {
        navigation(
            route = IScreenSpec.root,
            startDestination = IScreenSpec.startDestination
        ) {
            IScreenSpec.allScreens.forEach { (_, screen) ->
                if(screen != null) {
                    composable(
                        route = screen.route,
                        arguments = screen.arguments
                    ) { navBackStackEntry ->
                        screen.Content(
                            navController = navController,
                            navBackStackEntry = navBackStackEntry,
                            viewModel = viewModel,
                            context = context
                        )
                    }
                }
            }
        }
    }
}