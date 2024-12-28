package com.misaellima.recipeas.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.misaellima.recipeas.database.models.Recipe
import com.misaellima.recipeas.database.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Int): User

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserByEmail(email: String): User

    @Query("SELECT favoriteRecipes FROM User WHERE id = :id")
    fun getFavoriteRecipesRaw(id: Int): Flow<String>

//    @Query("SELECT favoriteRecipes FROM user WHERE id = :id")
//    fun getFavoriteRecipes(id: Int): Flow<List<Int>>

}