package com.ruthvik.multifeature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ruthvik.multifeature.common.AppTextView
import com.ruthvik.multifeature.common.TypographyType

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onSignInClicked: () -> Unit
) {
    Column(modifier = modifier) {
        AppTextView(
            text = "Sign In",
            modifier = Modifier,
            typographyType = TypographyType.Title
        )
        Spacer(modifier = Modifier.height(16.dp))

        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        InputField(
            inputType = InputType.Email,
            initialValue = email.value,
            onInputValueChange = { email.value = it },
        )
        Spacer(modifier = Modifier.height(8.dp))

        InputField(
            inputType = InputType.Password,
            initialValue = password.value,
            onInputValueChange = { password.value = it },
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = email.value.isNotEmpty() && password.value.isNotEmpty(),
            onClick = { onSignInClicked.invoke() },
            content = {
                AppTextView(text = "Sign In", typographyType = TypographyType.Label)
            }
        )
    }
}