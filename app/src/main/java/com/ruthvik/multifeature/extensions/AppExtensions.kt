package com.ruthvik.multifeature.extensions

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.ruthvik.multifeature.MultiFeatureApplication

val Context.dataStore by preferencesDataStore(name = "multi-feature-app-preferences")

fun CreationExtras.multiFeatureApplication(): MultiFeatureApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MultiFeatureApplication)