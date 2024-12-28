package com.misaellima.recipeas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.misaellima.recipeas.database.dao.RecipeDao
import com.misaellima.recipeas.database.dao.UserDao
import com.misaellima.recipeas.database.models.Recipe
import com.misaellima.recipeas.database.models.User


@Database(entities = [User::class, Recipe::class], version = 1)
@TypeConverters(Converter::class)
abstract class RecipeasDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun recipeDao(): RecipeDao
}

