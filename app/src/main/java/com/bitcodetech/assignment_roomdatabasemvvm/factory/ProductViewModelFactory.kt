package com.bitcodetech.assignment_roomdatabasemvvm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.assignment_roomdatabasemvvm.repository.ProductRepository
import com.bitcodetech.assignment_roomdatabasemvvm.viewmodels.ProductViewModel

class ProductViewModelFactory(private val productRepository: ProductRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(productRepository) as T
    }

}