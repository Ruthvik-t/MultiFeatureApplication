package com.ruthvik.multifeature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruthvik.multifeature.common.LoadingProgressBar
import com.ruthvik.multifeature.theme.MyApplicationTheme
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
                    val uiState by mainActivityViewModel.uiState.collectAsState()
                    MainScreenContent(
                        uiState = uiState,
                        modifier = Modifier.padding(innerPadding),
                        onSignInClicked = { },
                        onRegisterClicked = { },
                    )
                }
            }
        }
    }

    @Composable
    private fun MainScreenContent(
        uiState: UiState,
        modifier: Modifier = Modifier,
        onSignInClicked: () -> Unit,
        onRegisterClicked: () -> Unit
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           when(uiState) {
               UiState.Loading -> { LoadingProgressBar() }
               is UiState.LoggedInState -> {
                   if(uiState.isLoggedIn) {
                       // show loggedIn screen
                   } else {
                       AnonymousScreen(onSignInClicked, onRegisterClicked)
                   }
               }
               else -> { }
           }
        }
    }
}

@Composable
fun AnonymousScreen(
    onSignInClicked: () -> Unit,
    onRegisterClicked: () -> Unit
) {
    Column {
        Greeting(
            name = "Android",
            modifier = Modifier.padding(16.dp)
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onSignInClicked.invoke() }
        ){
            Text(text = "Sign In")
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onRegisterClicked.invoke() }
        ) {
            Text(text = "Register")
        }
    }

}

@Preview
@Composable
fun AnonymousScreenPreview() {
    MyApplicationTheme {
        AnonymousScreen(onSignInClicked = { }) { }
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