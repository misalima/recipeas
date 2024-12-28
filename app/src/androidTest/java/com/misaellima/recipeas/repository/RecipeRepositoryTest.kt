package com.misaellima.recipeas.repository

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.misaellima.recipeas.database.RecipeasDatabase
import com.misaellima.recipeas.database.dao.RecipeDao
import com.misaellima.recipeas.database.dao.UserDao
import com.misaellima.recipeas.database.models.Recipe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipeRepositoryTest {
    private lateinit var database: RecipeasDatabase
    private lateinit var recipeRepository: RecipeRepository
    private lateinit var recipeDao: RecipeDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RecipeasDatabase::class.java
        ).allowMainThreadQueries().build()

        recipeDao = database.recipeDao()
        recipeRepository = RecipeRepository(recipeDao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testAddRecipeAndGetRecipe() = runTest{
        // Arrange
        val recipe = Recipe(
            id = 1,
            title = "Pasta",
            category = listOf("Italian"),
            ingredients = listOf("Pasta", "Sauce"),
            instructions = listOf("Boil pasta", "Add sauce"),
            image = "pasta.jpg",
            description = "Delicious pasta recipe",
        )
        // Act
        recipeRepository.addRecipe(recipe)
        val retrievedRecipe = recipeRepository.getRecipeById(recipe.id)
        // Assert
        Assert.assertEquals(retrievedRecipe, recipe)
    }

    @Test
    fun testGetRecipesByCategory() = runTest{
        // Arrange
        val recipe1 = Recipe(
            id = 1,
            title = "Pasta",
            category = listOf("Italian"),
            ingredients = listOf("Pasta", "Sauce"),
            instructions = listOf("Boil pasta", "Add sauce"),
            image = "pasta.jpg",
            description = "Delicious pasta recipe",
        )
        val recipe2 = Recipe(
            id = 2,
            title = "Sushi",
            category = listOf("Japanese"),
            ingredients = listOf("Rice", "Fish"),
            instructions = listOf("Cook rice", "Add fish"),
            image = "sushi.jpg",
            description = "Delicious sushi recipe",
        )
        recipeRepository.addRecipe(recipe1)
        recipeRepository.addRecipe(recipe2)
        // Act
        val retrievedRecipes = recipeRepository.getRecipesByCategory("Italian").first()

        // Assert
        Assert.assertEquals(retrievedRecipes.size, 1)
        Assert.assertEquals(retrievedRecipes[0], recipe1)
    }

    @Test
    fun testGetAllRecipes() = runTest{
        // Arrange
        val recipe1 = Recipe(
            id = 1,
            title = "Pasta",
            category = listOf("Italian"),
            ingredients = listOf("Pasta", "Sauce"),
            instructions = listOf("Boil pasta", "Add sauce"),
            image = "pasta.jpg",
            description = "Delicious pasta recipe",
        )
        val recipe2 = Recipe(
            id = 2,
            title = "Sushi",
            category = listOf("Japanese"),
            ingredients = listOf("Rice", "Fish"),
            instructions = listOf("Cook rice", "Add fish"),
            image = "sushi.jpg",
            description = "Delicious sushi recipe",
        )
        recipeRepository.addRecipe(recipe1)
        recipeRepository.addRecipe(recipe2)
        // Act
        val retrievedRecipes = recipeRepository.getAllRecipes().first()
        // Assert
        Assert.assertEquals(retrievedRecipes.size, 2)
        Assert.assertEquals(retrievedRecipes[0], recipe1)
        Assert.assertEquals(retrievedRecipes[1], recipe2)
    }

}