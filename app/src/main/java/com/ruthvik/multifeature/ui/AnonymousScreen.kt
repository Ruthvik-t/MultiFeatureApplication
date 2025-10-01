package com.ruthvik.multifeature.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruthvik.multifeature.Greeting
import com.ruthvik.multifeature.theme.MyApplicationTheme

@Composable
fun AnonymousScreen(
    onSignInClicked: () -> Unit,
    onRegisterClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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