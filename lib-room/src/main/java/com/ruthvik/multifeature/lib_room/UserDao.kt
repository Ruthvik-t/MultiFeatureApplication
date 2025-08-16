package com.ruthvik.multifeature.lib_room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ruthvik.multifeature.lib_room.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    /*@Query("SELECT * FROM users WHERE email = :email")
    fun getUserByEmail(email: String): Flow<User>*/

}