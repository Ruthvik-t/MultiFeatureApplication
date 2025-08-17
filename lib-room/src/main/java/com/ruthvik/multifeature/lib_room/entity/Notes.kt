package com.ruthvik.multifeature.lib_room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["email"],
            childColumns = ["email"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Note(
    @PrimaryKey val id: Int,
    val email: String,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long,
)