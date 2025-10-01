package com.ruthvik.multifeature.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class MainScreen: NavKey {

    @Serializable
    data object Loading: MainScreen()

    @Serializable
    data object AnonymousScreen: MainScreen()

    @Serializable
    data object LoginScreen: MainScreen()

    @Serializable
    data object RegisterScreen: MainScreen()

    @Serializable
    data class AuthorizedScreen(val email: String): MainScreen()
}