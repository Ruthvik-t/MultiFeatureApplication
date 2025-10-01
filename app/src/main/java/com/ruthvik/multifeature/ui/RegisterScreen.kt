package com.ruthvik.multifeature.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ruthvik.multifeature.common.AppTextView
import com.ruthvik.multifeature.common.TypographyType
import com.ruthvik.multifeature.common.ValidationUtils
import com.ruthvik.multifeature.lib_room.entity.User
import com.ruthvik.multifeature.theme.DeepPurple

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onRegisterClicked: (User) -> Unit
    ) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTextView(
            text = "Register",
            modifier = modifier,
            typographyType = TypographyType.Title
        )
        Spacer(modifier = Modifier.height(16.dp))

        val name = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        InputField(
            inputType = InputType.Name,
            initialValue = name.value,
            onInputValueChange = { name.value = it },
        )
        Spacer(modifier = Modifier.height(8.dp))

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
            enabled = name.value.isNotEmpty() && email.value.isNotEmpty() && password.value.isNotEmpty(),
            onClick = { onRegisterClicked.invoke(
                User(
                    name = name.value,
                    email = email.value,
                    password = password.value
                )
            ) },
            content = {
                AppTextView(text = "Register", typographyType = TypographyType.Label)
            }
        )

    }
}

@Composable
internal fun InputField(
    inputType: InputType,
    initialValue: String = "",
    onInputValueChange: (String) -> Unit = { },
) {
    val inputText = remember { mutableStateOf(initialValue) }
    val isError = remember { mutableStateOf(false) }
    val supportingText = remember { mutableStateOf<String?>(null) }
    OutlinedTextField(
        value = inputText.value,
        onValueChange = { it
            inputText.value = it
            onInputValueChange.invoke(it)
            when(inputType){
                InputType.Email -> {
                    if(!ValidationUtils.isValidEmail(it)){
                        isError.value = true
                        supportingText.value = "Invalid Email format"
                    } else {
                        supportingText.value = null
                    }
                }
                InputType.Password -> {
                    inputText.value = it
                    if(!ValidationUtils.isValidPassword(it)){
                        isError.value = true
                        supportingText.value = ValidationUtils.getPasswordErrorMessage(it)
                    } else {
                        supportingText.value = null
                    }
                }
                else -> { }
            }
        },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        placeholder = {
            when(inputType) {
                InputType.Email -> AppTextView(text = "someone@example.com", typographyType = TypographyType.Subtext)
                InputType.Password -> AppTextView(text = "some password", typographyType = TypographyType.Subtext)
                InputType.Name -> AppTextView(text = "Your name", typographyType = TypographyType.Subtext)
            }
        },
        trailingIcon = {
            if(inputText.value.isNotEmpty()){
                Icon(
                    imageVector = Icons.Sharp.Close,
                    tint = DeepPurple, contentDescription = "clear",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            inputText.value = ""
                            supportingText.value = null
                        }
                )
            }
        },
        supportingText = {
            supportingText.value?.let {
                AppTextView(text = it, typographyType = TypographyType.Subtext)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = when(inputType){
                InputType.Email -> KeyboardType.Email
                InputType.Password -> KeyboardType.Password
                else -> { KeyboardType.Unspecified }
            }
        )
    )
}

enum class InputType {
    Email,
    Password,
    Name,
}