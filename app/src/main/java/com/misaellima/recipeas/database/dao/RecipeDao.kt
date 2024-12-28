package com.misaellima.recipeas.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.misaellima.recipeas.database.models.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Upsert
    suspend fun upsertRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe WHERE id = :id")
    fun getRecipeById(id: Int): Recipe

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): Flow<List<Recipe>>
}