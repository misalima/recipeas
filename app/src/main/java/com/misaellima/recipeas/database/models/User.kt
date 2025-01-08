package com.misaellima.recipeas.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val profilePicture: String = "",
    val favoriteRecipes: List<Int> = emptyList()
)
