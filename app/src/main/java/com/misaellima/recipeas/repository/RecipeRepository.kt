package com.misaellima.recipeas.repository

import com.misaellima.recipeas.database.dao.RecipeDao
import com.misaellima.recipeas.database.models.Recipe
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeDao: RecipeDao) {
    suspend fun addRecipe(recipe: Recipe) {
        recipeDao.upsertRecipe(recipe)
    }

    suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.upsertRecipe(recipe)
    }

    fun getRecipeById(id: Int): Recipe {
        return recipeDao.getRecipeById(id)
    }

    fun getRecipesByCategory(category: String): Flow<List<Recipe>> {
        return recipeDao.getRecipesByCategory(category)
    }

    fun getAllRecipes(): Flow<List<Recipe>> {
        return recipeDao.getAllRecipes()
    }
}