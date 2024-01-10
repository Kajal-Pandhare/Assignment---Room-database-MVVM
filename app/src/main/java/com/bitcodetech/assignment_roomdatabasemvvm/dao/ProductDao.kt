package com.bitcodetech.assignment_roomdatabasemvvm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bitcodetech.assignment_roomdatabasemvvm.entity.Product

@Dao
interface ProductDao {
    @Query("select * from products")

    fun getProducts() : List<Product>

    @Insert

    fun insertProduct(product: Product)

}