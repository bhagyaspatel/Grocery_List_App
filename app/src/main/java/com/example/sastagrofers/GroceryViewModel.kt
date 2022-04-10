package com.example.sastagrofers

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel (private val groceryRepository: GroceryRepository) : ViewModel() {

    fun insert (items: GroceryItems) = GlobalScope.launch {
        groceryRepository.insert(items)
    }

    fun delete (items: GroceryItems) = GlobalScope.launch {
        groceryRepository.delete(items)
    }

    fun getAllGroceryItems() = groceryRepository.getAllItems()
}