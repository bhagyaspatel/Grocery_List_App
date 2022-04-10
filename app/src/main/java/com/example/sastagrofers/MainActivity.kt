package com.example.sastagrofers

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), GroceryRVAdapter.GroceryAdapterInterface {

    lateinit var recyclerView: RecyclerView
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var list : List<GroceryItems>
    lateinit var groceryRVAdapter: GroceryRVAdapter
    lateinit var groceryViewModel: GroceryViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewItems)
        floatingActionButton = findViewById(R.id.AddButton)

        list = ArrayList<GroceryItems> ()
        groceryRVAdapter = GroceryRVAdapter(list, this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = groceryRVAdapter

        val repository = GroceryRepository(GroceryDataBase(this))
        val factoryObject = GroceryViewModelFactory(repository)

        groceryViewModel = ViewModelProvider (this, factoryObject).get(GroceryViewModel::class.java)

        groceryViewModel.getAllGroceryItems().observe(this) {
            groceryRVAdapter.list = it
            groceryRVAdapter.notifyDataSetChanged()
        }

        floatingActionButton.setOnClickListener{
            openDialog()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.grocery_add_card)

        val cancelButton = dialog.findViewById<AppCompatButton>(R.id.cancel_button)
        val addButton = dialog.findViewById<AppCompatButton>(R.id.add_button)
        val productName = dialog.findViewById<EditText>(R.id.EditProductName)
        val productPrice = dialog.findViewById<EditText>(R.id.EditPrice)
        val productQuantity = dialog.findViewById<EditText>(R.id.EditQuantity)

        cancelButton.setOnClickListener{
            dialog.dismiss()
        }

        addButton.setOnClickListener{
            Log.d("Hello", "Hello")
            val product : String = productName.text.toString() //****it was productName.text.toString()
            val price : String = productPrice.text.toString()
            val quantity : String = productQuantity.text.toString()

            var prc : Int = 0
            if (price.isNotEmpty())
                prc = price.toInt()

            var qnty : Int = 0

            if (quantity.isNotEmpty())
            qnty = quantity.toInt()

            if (product.isNotEmpty() && price.isNotEmpty()){
                if (quantity.isEmpty())
                    qnty = 1

                val items = GroceryItems (product, prc, qnty)
                groceryViewModel.insert(items)
                Toast.makeText(applicationContext, "Item Added", Toast.LENGTH_SHORT).show()
                groceryRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            else{
                Toast.makeText(applicationContext, "Please enter all data", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onItemClicked(item: GroceryItems) {
        groceryViewModel.delete(item)
        groceryRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Item Deleted", Toast.LENGTH_SHORT).show()
    }

}