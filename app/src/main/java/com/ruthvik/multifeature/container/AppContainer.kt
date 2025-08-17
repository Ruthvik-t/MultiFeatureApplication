package com.ruthvik.multifeature.container

import android.content.Context
import com.ruthvik.multifeature.lib_room.DatabaseModule
import com.ruthvik.multifeature.lib_room.repository.UserRepository
import com.ruthvik.multifeature.lib_room.repository.UserRepositoryImpl
import com.ruthvik.multifeature.repository.AppSharedPreferenceRepository
import com.ruthvik.multifeature.repository.AppSharedPreferenceRepositoryImpl

interface AppContainer {

    val userRepository: UserRepository

    val sharedPreferenceRepository: AppSharedPreferenceRepository
}

class AppDataContainer(context: Context): AppContainer {

    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(
            userDao = DatabaseModule.provideUserDao(DatabaseModule.provideDatabase(context))
        )
    }

    override val sharedPreferenceRepository: AppSharedPreferenceRepository by lazy {
        AppSharedPreferenceRepositoryImpl(context)
    }
}