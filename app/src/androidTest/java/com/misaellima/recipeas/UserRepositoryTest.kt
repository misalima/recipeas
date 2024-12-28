package com.misaellima.recipeas

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.misaellima.recipeas.database.RecipeasDatabase
import com.misaellima.recipeas.database.dao.UserDao
import com.misaellima.recipeas.database.models.User
import com.misaellima.recipeas.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private lateinit var database: RecipeasDatabase
    private lateinit var userRepository: UserRepository
    private lateinit var userDao: UserDao


    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RecipeasDatabase::class.java
        ).allowMainThreadQueries().build()

        userDao = database.userDao()
        userRepository = UserRepository(userDao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testAddUserAndGetUser() = runTest {

        val user = User(
            id = 1,
            name = "John Doe",
            email = "jd@mail.com",
            password = "123456",
            profilePicture = "profile.jpg",
            favoriteRecipes = emptyList()
        )

        userRepository.addUser(user)
        val retrievedUser = userRepository.getUserByEmail(user.email)

        Assert.assertEquals(retrievedUser, user)
    }

    @Test
    fun tesGetFavoriteRecipes() = runTest {
        // Arrange
        val user = User(
            id = 1,
            name = "John Doe",
            email = "jd@mail.com",
            password = "123456",
            profilePicture = "profile.jpg",
            favoriteRecipes = listOf(1, 2, 3)
        )
        userRepository.addUser(user)

        // Act
        val favoriteRecipes = userRepository.getFavoriteRecipes(1).first()

        // Assert
        Assert.assertEquals(listOf(1, 2, 3), favoriteRecipes)
    }

    @Test
    fun testAddFavoriteRecipes() = runTest {
        // Arrange
        val user = User(
            id = 1,
            name = "John Doe",
            email = "jd@mail.com",
            password = "123456",
            profilePicture = "profile.jpg",
            favoriteRecipes = listOf(1, 2) // Initial favorite recipes
        )
        userRepository.addUser(user)

        // Act
        userRepository.addFavoriteRecipe(user.id, 4)

        // Assert
        val updatedUser = userRepository.getUserById(user.id)
        val expectedFavorites = listOf(1, 2, 4)
        Assert.assertEquals(expectedFavorites, updatedUser.favoriteRecipes)
    }

    @Test
    fun testRemoveFavoriteRecipes() = runTest {
        // Arrange
        val user = User(
            id = 1,
            name = "John Doe",
            email = "jd@mail.com",
            password = "123456",
            profilePicture = "profile.jpg",
            favoriteRecipes = listOf(1, 2, 3, 4) // Initial favorite recipes
        )
        userRepository.addUser(user)

        // Act
        userRepository.removeFavoriteRecipe(user.id, 3)

        // Assert
        val updatedUser = userRepository.getUserById(user.id)
        val expectedFavorites = listOf(1, 2, 4)
        Assert.assertEquals(expectedFavorites, updatedUser.favoriteRecipes)
    }
}