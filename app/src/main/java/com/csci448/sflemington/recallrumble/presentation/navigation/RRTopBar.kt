package com.csci448.sflemington.recallrumble.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.csci448.sflemington.recallrumble.presentation.navigation.specs.IScreenSpec
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel

@Composable
fun RRTopBar(viewModel: IViewModel, navController: NavHostController, context: Context){
    val navBackStackEntryState = navController.currentBackStackEntryAsState()
    IScreenSpec.TopBar(viewModel, navController, navBackStackEntryState.value)
}