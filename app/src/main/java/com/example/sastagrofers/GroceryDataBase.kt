package com.example.sastagrofers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GroceryItems::class], version = 1)
abstract class GroceryDataBase : RoomDatabase() {

    abstract fun getGroceryDao() : GroceryDao

    companion object {

        @Volatile
        private var instance: GroceryDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createGroceryDatabase(context).also {
                instance = it
            }
        }

        private fun createGroceryDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GroceryDataBase::class.java,
                "Grocery.db"
            ).build()
    }
}