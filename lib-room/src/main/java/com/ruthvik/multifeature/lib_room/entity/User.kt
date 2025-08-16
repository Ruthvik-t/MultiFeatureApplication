package com.ruthvik.multifeature.lib_room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val name: String,
    @PrimaryKey val email: String,
    val password: String
)
