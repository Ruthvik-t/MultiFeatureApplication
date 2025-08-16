package com.ruthvik.multifeature.lib_room

import android.content.Context
import androidx.room.Room

object DatabaseModule {

    @Volatile
    private var appDatabase: AppDatabase? = null

    fun provideDatabase(context: Context): AppDatabase {
        return appDatabase ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build()
        }
    }

    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }
}