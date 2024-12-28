package com.misaellima.recipeas.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    @Volatile
    private var INSTANCE: RecipeasDatabase? = null

    fun getDatabase(context: Context): RecipeasDatabase {
        return INSTANCE ?: synchronized(this) {
            val dbFile = context.getDatabasePath("recipeas.db")
            val instance = Room.databaseBuilder(
                context.applicationContext,
                RecipeasDatabase::class.java,
                dbFile.absolutePath
            ).build()
            INSTANCE = instance
            instance
        }
    }
}