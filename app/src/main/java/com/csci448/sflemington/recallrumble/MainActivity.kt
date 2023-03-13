package com.csci448.sflemington.recallrumble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.csci448.sflemington.recallrumble.presentation.navigation.RRBottomBar
import com.csci448.sflemington.recallrumble.presentation.navigation.RRNavHost
import com.csci448.sflemington.recallrumble.presentation.navigation.RRTopBar
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel
import com.csci448.sflemington.recallrumble.ui.theme.RecallRumbleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecallRumbleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    MainActivityContent()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecallRumbleTheme {

    }
}

@Composable
private fun MainActivityContent(viewModel: IViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current

    RecallRumbleTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    RRTopBar(viewModel, navController, context)
                },
                bottomBar = {
                    RRBottomBar(viewModel, navController, context)
                }
            ){
                val paddingValues = it
                RRNavHost(modifier = Modifier.padding(paddingValues), navController = navController, viewModel = viewModel, context = context)
            }

        }
    }
}