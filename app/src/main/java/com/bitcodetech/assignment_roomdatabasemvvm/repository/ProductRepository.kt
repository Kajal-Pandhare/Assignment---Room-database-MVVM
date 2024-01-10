package com.bitcodetech.assignment_roomdatabasemvvm.repository

import com.bitcodetech.assignment_roomdatabasemvvm.dao.ProductDao
import com.bitcodetech.assignment_roomdatabasemvvm.entity.Product

class ProductRepository(private val productDao  :ProductDao
) {

    suspend fun fetchProduct():List<Product>{
        return productDao.getProducts()
    }

}