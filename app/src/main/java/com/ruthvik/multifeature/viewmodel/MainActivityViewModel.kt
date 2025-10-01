package com.ruthvik.multifeature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruthvik.multifeature.lib_room.entity.User
import com.ruthvik.multifeature.lib_room.repository.UserRepository
import com.ruthvik.multifeature.repository.AppSharedPreferenceRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val userRepository: UserRepository,
    private val sharedPreferenceRepository: AppSharedPreferenceRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            delay(3000L)
            sharedPreferenceRepository.userEmail.collect { email ->
                _uiState.value = UiState.LoggedInState(isLoggedIn = !email.isNullOrEmpty(), userEmail = email)
            }
        }
    }

    fun registerUser(user: User) {
        viewModelScope.launch {
            userRepository.insertUser(user)
            saveUserEmail(user.email)
            delay(3000L)
            _uiState.value = UiState.LoggedInState(isLoggedIn = true, userEmail = user.email)
        }
    }

    fun loginUser(email: String, password: String) {

    }

    fun saveUserEmail(email: String) {
        viewModelScope.launch {
            sharedPreferenceRepository.saveUserEmail(email)
        }
    }

    fun updateUiState(state: UiState) {
        _uiState.value = state
    }

}

sealed class UiState {

    data object Loading: UiState()

    data class LoggedInState(val isLoggedIn: Boolean, val userEmail: String?): UiState()

    data class Error(val throwable: Throwable): UiState()

    object LaunchSignInScreen: UiState()

    object LaunchRegisterScreen: UiState()
}
