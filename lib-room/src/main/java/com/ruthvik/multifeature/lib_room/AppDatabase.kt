package com.ruthvik.multifeature.lib_room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ruthvik.multifeature.lib_room.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}