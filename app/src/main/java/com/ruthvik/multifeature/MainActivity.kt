package com.ruthvik.multifeature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.ruthvik.multifeature.common.AppTextView
import com.ruthvik.multifeature.common.LoadingProgressBar
import com.ruthvik.multifeature.common.TypographyType
import com.ruthvik.multifeature.navigation.MainScreen
import com.ruthvik.multifeature.theme.MyApplicationTheme
import com.ruthvik.multifeature.ui.AnonymousScreen
import com.ruthvik.multifeature.ui.HomeScreen
import com.ruthvik.multifeature.ui.RegisterScreen
import com.ruthvik.multifeature.ui.SignInScreen
import com.ruthvik.multifeature.viewmodel.MainActivityViewModel
import com.ruthvik.multifeature.viewmodel.UiState

class MainActivity : ComponentActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels {
        AppViewModelProvider.factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val uiState by mainActivityViewModel.uiState.collectAsStateWithLifecycle()
                    val mainScreenBackStack = rememberNavBackStack<MainScreen>(MainScreen.Loading)

                    NavDisplay(
                        backStack = mainScreenBackStack,
                        onBack = {
                            mainScreenBackStack.removeLastOrNull()
                                 },
                        entryDecorators = listOf(
                            rememberSavedStateNavEntryDecorator(),
                            rememberViewModelStoreNavEntryDecorator(),
                        ),
                        modifier = Modifier.padding(16.dp),
                        entryProvider = entryProvider {
                            entry<MainScreen.Loading> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center) {
                                    LoadingProgressBar()
                                }
                            }
                            entry<MainScreen.AnonymousScreen> {
                                AnonymousScreen(
                                    onSignInClicked = { mainActivityViewModel.updateUiState(UiState.LaunchSignInScreen) },
                                    onRegisterClicked = { mainActivityViewModel.updateUiState(UiState.LaunchRegisterScreen) }
                                )
                            }
                            entry<MainScreen.LoginScreen> {
                                Box(
                                    modifier = Modifier.fillMaxSize().padding(top = 32.dp),
                                    contentAlignment = Alignment.TopCenter,
                                ) {
                                    SignInScreen {
                                        // to be implemented
                                    }
                                }
                            }
                            entry<MainScreen.RegisterScreen> {
                                RegisterScreen { user ->
                                    mainActivityViewModel.updateUiState(UiState.Loading)
                                    mainActivityViewModel.registerUser(user)
                                }
                            }
                            entry<MainScreen.AuthorizedScreen> { entry ->
                                HomeScreen(name = entry.email)
                            }
                        }
                    )
                    MainScreenContent(
                        uiState = uiState,
                        modifier = Modifier.padding(innerPadding),
                        backstack = mainScreenBackStack,
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun MainScreenContent(
        uiState: UiState,
        modifier: Modifier = Modifier,
        backstack: NavBackStack
    ) {
        Column(
            modifier = modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           when(uiState) {
               UiState.Loading -> {
                   if(backstack.isNotEmpty() && backstack.last() !is MainScreen.Loading) {
                       backstack.add(MainScreen.Loading)
                   }
               }
               is UiState.LoggedInState -> {
                   if(uiState.isLoggedIn) {
                       backstack.add(MainScreen.AuthorizedScreen(uiState.userEmail.orEmpty()))
                   } else {
                       backstack.removeLastOrNull()
                       backstack.add(MainScreen.AnonymousScreen)
                   }
               }
               is UiState.LaunchSignInScreen -> {
                   backstack.add(MainScreen.LoginScreen)
               }
               is UiState.LaunchRegisterScreen -> {
                   backstack.add(MainScreen.RegisterScreen)
               }
               is UiState.Error -> {
                   Text(text = uiState.throwable.message?: "Unknown error")
                   BasicAlertDialog(
                       onDismissRequest = { }
                   ) {
                       AppTextView(
                           text = uiState.throwable.message?: "Unknown error",
                           typographyType = TypographyType.Label,
                           modifier = Modifier.padding(top = 8.dp)
                       )
                   }
               }
           }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}