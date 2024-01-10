package com.bitcodetech.assignment_roomdatabasemvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.assignment_roomdatabasemvvm.entity.Product
import com.bitcodetech.assignment_roomdatabasemvvm.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel(private val productRepository: ProductRepository
):ViewModel() {

    val productMutableLiveData = MutableLiveData<Boolean>()
    val products = ArrayList<Product>()

    fun fetchProducts(){
        CoroutineScope(Dispatchers.IO).launch {
            val products = productRepository.fetchProduct()

            withContext(Dispatchers.Main){
                this@ProductViewModel.products.addAll(products)
                productMutableLiveData.postValue(true)
            }
        }
    }

}