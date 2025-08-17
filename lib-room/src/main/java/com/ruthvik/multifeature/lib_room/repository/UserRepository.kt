package com.ruthvik.multifeature.lib_room.repository

import com.ruthvik.multifeature.lib_room.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getAllUsers(): Flow<List<User>>

    suspend fun insertUser(user: User)

    fun getUserByEmail(email: String): Flow<User>
}