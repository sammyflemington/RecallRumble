package com.csci448.sflemington.recallrumble

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.csci448.sflemington.recallrumble.presentation.navigation.RRBottomBar
import com.csci448.sflemington.recallrumble.presentation.navigation.RRNavHost
import com.csci448.sflemington.recallrumble.presentation.navigation.RRTopBar
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel
import com.csci448.sflemington.recallrumble.presentation.viewmodel.RRViewModel
import com.csci448.sflemington.recallrumble.presentation.viewmodel.RRViewModelFactory
import com.csci448.sflemington.recallrumble.ui.theme.RecallRumbleTheme
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth


class MainActivity : ComponentActivity() {
    companion object {
        private const val LOG_TAG = "448.MainActivity"
    }
    private lateinit var mRRViewModel: RRViewModel

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { result: FirebaseAuthUIAuthenticationResult? -> }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate() called")

        val factory = RRViewModelFactory(this)
        mRRViewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()]

        setContent {
            RecallRumbleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val auth = FirebaseAuth.getInstance()
                    if (auth.currentUser != null) {
                        // already signed in
                    } else {
                        // not signed in
                        val signInIntent = AuthUI.getInstance().createSignInIntentBuilder().build()
                        signInLauncher.launch(signInIntent)
                    }
                    MainActivityContent(viewModel = mRRViewModel)
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

@OptIn(ExperimentalMaterial3Api::class)
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
                //CategoryListScreen(categories = CategoryRepository.categoryList)
                RRNavHost(modifier = Modifier.padding(paddingValues), navController = navController, viewModel = viewModel, context = context)
            }

        }
    }
}