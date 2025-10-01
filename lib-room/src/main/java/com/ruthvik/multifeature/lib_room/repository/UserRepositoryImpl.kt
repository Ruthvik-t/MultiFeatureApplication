package com.ruthvik.multifeature.lib_room.repository

import com.ruthvik.multifeature.lib_room.UserDao
import com.ruthvik.multifeature.lib_room.entity.User
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    override suspend fun insertUser(user: User): Boolean {
        return try {
            val rowId = userDao.insert(user)
            rowId > 0
        } catch (e: Exception) {
            false
        }
    }

    override fun getUserByEmail(email: String): Flow<User> {
        return userDao.getUserByEmail(email)
    }
}