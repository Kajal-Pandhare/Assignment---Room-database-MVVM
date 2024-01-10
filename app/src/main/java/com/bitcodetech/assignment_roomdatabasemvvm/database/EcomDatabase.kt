package com.bitcodetech.assignment_roomdatabasemvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bitcodetech.assignment_roomdatabasemvvm.dao.ProductDao
import com.bitcodetech.assignment_roomdatabasemvvm.entity.Product

@Database(version = 1, entities = [Product::class])
abstract class EcomDatabase: RoomDatabase() {

    abstract fun getProductDao() : ProductDao

    companion object{
        private var ecomDatabase : EcomDatabase? = null

        fun getInstance(context: Context):EcomDatabase{
            if (ecomDatabase == null){
               ecomDatabase =  Room.databaseBuilder(context,
                    EcomDatabase::class.java,
                    "ecomm_db"
                ).build()
            }
            return ecomDatabase!!
        }
    }

}