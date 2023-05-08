package com.csci448.sflemington.recallrumble

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.csci448.sflemington.recallrumble.data.CategoryRepository
import com.csci448.sflemington.recallrumble.presentation.navigation.RRBottomBar
import com.csci448.sflemington.recallrumble.presentation.navigation.RRNavHost
import com.csci448.sflemington.recallrumble.presentation.navigation.RRTopBar
import com.csci448.sflemington.recallrumble.presentation.screens.CategoryListScreen
import com.csci448.sflemington.recallrumble.presentation.viewmodel.IViewModel
import com.csci448.sflemington.recallrumble.presentation.viewmodel.RRViewModel
import com.csci448.sflemington.recallrumble.presentation.viewmodel.RRViewModelFactory
import com.csci448.sflemington.recallrumble.ui.theme.RecallRumbleTheme

class MainActivity : ComponentActivity() {
    companion object {
        private const val LOG_TAG = "448.MainActivity"
    }
    private lateinit var mRRViewModel: RRViewModel

    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }


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
