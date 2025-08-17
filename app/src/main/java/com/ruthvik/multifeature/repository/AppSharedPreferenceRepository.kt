package com.ruthvik.multifeature.repository

import kotlinx.coroutines.flow.Flow

interface AppSharedPreferenceRepository {

    val userEmail: Flow<String?>

    suspend fun saveUserEmail(email: String)
}