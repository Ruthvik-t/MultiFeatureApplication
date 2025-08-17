package com.ruthvik.multifeature.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ruthvik.multifeature.extensions.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppSharedPreferenceRepositoryImpl(private val context: Context) : AppSharedPreferenceRepository {
    override val userEmail: Flow<String?>
        get() = context.dataStore.data.map { prefs -> prefs[EMAIL_PREFERENCES_KEY] }

    override suspend fun saveUserEmail(email: String) {
        context.dataStore.edit { prefs ->
            prefs[EMAIL_PREFERENCES_KEY] = email  // Saving the email to preferences
        }
    }

    companion object{
        val EMAIL_PREFERENCES_KEY = stringPreferencesKey("user_email")
    }
}