package com.example.sastagrofers

class GroceryRepository (private val db : GroceryDataBase) {

    suspend fun insert (items: GroceryItems) = db.getGroceryDao().insert(items)
    suspend fun  delete (items: GroceryItems) = db.getGroceryDao().delete(items)

    fun getAllItems() = db.getGroceryDao().getAllGroceryItems()
}