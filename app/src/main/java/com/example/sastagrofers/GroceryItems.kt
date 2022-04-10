package com.example.sastagrofers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "grocery_items")
class GroceryItems (

    @ColumnInfo (name = "ItemName")
    var ItemName : String ,

    @ColumnInfo (name = "ItemValue")
    var ItemValue : Int ,

    @ColumnInfo (name = "ItemQuantity")
    var ItemQuantity : Int,

    ){

    @PrimaryKey (autoGenerate = true)
    var id : Int = 0 ///*******
}