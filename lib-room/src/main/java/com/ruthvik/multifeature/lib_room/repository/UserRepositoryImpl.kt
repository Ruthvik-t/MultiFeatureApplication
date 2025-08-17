package com.ruthvik.multifeature.lib_room.repository

import com.ruthvik.multifeature.lib_room.UserDao
import com.ruthvik.multifeature.lib_room.entity.User
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    override suspend fun insertUser(user: User) {
        userDao.insert(user)
    }

    override fun getUserByEmail(email: String): Flow<User> {
        return userDao.getUserByEmail(email)
    }
}