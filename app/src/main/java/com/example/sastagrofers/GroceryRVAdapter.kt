package com.example.sastagrofers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceryRVAdapter (var list : List<GroceryItems>, val listener : GroceryAdapterInterface)
    : RecyclerView.Adapter<GroceryRVAdapter.GroceryViewHolder>() {

    inner class GroceryViewHolder (itemview : View) : RecyclerView.ViewHolder(itemview){
        val product = itemview.findViewById<TextView>(R.id.productName)
        val quantity = itemview.findViewById<TextView>(R.id.Quantity)
        val amt = itemview.findViewById<TextView>(R.id.totalAmt)
        val deleteButton = itemview.findViewById<ImageView>(R.id.deleteIcon)
        val itemRate = itemview.findViewById<TextView>(R.id.itemRate)
    }

    interface GroceryAdapterInterface {
        fun onItemClicked (item : GroceryItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from (parent.context).inflate(R.layout.grocery_rv_items, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.product.text = list.get(position).ItemName
        holder.quantity.text = list.get(position).ItemQuantity.toString()
        holder.itemRate.text = "Rs. " + list.get(position).ItemValue.toString()
        val value : Int = list.get(position).ItemQuantity * list.get(position).ItemValue
        holder.amt.text = "Rs. " + value.toString()

        holder.deleteButton.setOnClickListener{
            listener.onItemClicked(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}