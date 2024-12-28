package com.misaellima.recipeas.repository

import com.misaellima.recipeas.database.Converter
import com.misaellima.recipeas.database.dao.UserDao
import com.misaellima.recipeas.database.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository (private val userDao: UserDao) {

    suspend fun addUser(user: User) {
        userDao.upsertUser(user)
    }

    fun getUserById(id: Int): User {
       return userDao.getUserById(id)
    }

    fun getUserByEmail(email: String): User {
        return userDao.getUserByEmail(email)
    }

    suspend fun upsertUser(user: User) {
        userDao.upsertUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    fun getFavoriteRecipes(id: Int): Flow<List<Int>> {
        return userDao.getFavoriteRecipesRaw(id).map { rawString ->
            Converter().toIntList(rawString)
        }
    }

    suspend fun addFavoriteRecipe(userId: Int, recipeId: Int) {
        val user = userDao.getUserById(userId)
        user?.let {
            val updatedFavorites = it.favoriteRecipes.toMutableList().apply { add(recipeId) }
            val updatedUser = it.copy(favoriteRecipes = updatedFavorites)
            userDao.upsertUser(updatedUser)
        }
    }

    suspend fun removeFavoriteRecipe(userId: Int, recipeId: Int) {
        val user = userDao.getUserById(userId)
        user?.let {
            val updatedFavorites = it.favoriteRecipes.toMutableList().apply { remove(recipeId) }
            val updatedUser = it.copy(favoriteRecipes = updatedFavorites)
            userDao.upsertUser(updatedUser)
        }
    }
}