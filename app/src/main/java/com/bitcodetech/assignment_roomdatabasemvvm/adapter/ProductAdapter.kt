package com.bitcodetech.assignment_roomdatabasemvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.assignment_roomdatabasemvvm.R
import com.bitcodetech.assignment_roomdatabasemvvm.entity.Product

class ProductAdapter(private val products : ArrayList<Product>):
RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val txtId : TextView
        val txtTitle : TextView
        val txtPrice : TextView

        init {
            txtId = view.findViewById(R.id.txtId)
            txtTitle = view.findViewById(R.id.txtTitle)
            txtPrice = view.findViewById(R.id.txtPrice)
        }
    }

    override fun getItemCount() = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_view,null)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        holder.txtId.text = product.id.toString()
        holder.txtTitle.text = product.title
        holder.txtPrice.text = product.price.toString()
    }
}