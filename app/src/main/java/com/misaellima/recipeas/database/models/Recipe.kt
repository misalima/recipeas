package com.misaellima.recipeas.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String?,
    val image: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val category: List<String>,
    val isFavorite: Boolean = false
)
