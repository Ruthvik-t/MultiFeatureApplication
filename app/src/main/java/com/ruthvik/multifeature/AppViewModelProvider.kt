package com.ruthvik.multifeature

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ruthvik.multifeature.extensions.multiFeatureApplication
import com.ruthvik.multifeature.viewmodel.MainActivityViewModel

object AppViewModelProvider {

    val factory = viewModelFactory {
        initializer {
            MainActivityViewModel(
                userRepository = multiFeatureApplication().appDataContainer.userRepository,
                sharedPreferenceRepository = multiFeatureApplication().appDataContainer.sharedPreferenceRepository
            )
        }
    }
}