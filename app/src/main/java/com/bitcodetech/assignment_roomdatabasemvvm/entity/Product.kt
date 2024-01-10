package com.bitcodetech.assignment_roomdatabasemvvm.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id : Int,
    val title : String,
    val price : Int
)
