package com.bitcodetech.assignment_roomdatabasemvvm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.assignment_roomdatabasemvvm.database.EcomDatabase
import com.bitcodetech.assignment_roomdatabasemvvm.databinding.AddProductFragmentBinding
import com.bitcodetech.assignment_roomdatabasemvvm.entity.Product
import com.bitcodetech.assignment_roomdatabasemvvm.factory.ProductViewModelFactory
import com.bitcodetech.assignment_roomdatabasemvvm.repository.ProductRepository
import com.bitcodetech.assignment_roomdatabasemvvm.viewmodels.ProductViewModel

class AddProductFragment : Fragment() {
    private lateinit var binding: AddProductFragmentBinding
    private lateinit var productViewModel: ProductViewModel

    interface OnAddProductListener{
        fun onAddProduct(product: Product)
    }

    var onAddProductListener : OnAddProductListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddProductFragmentBinding.inflate(layoutInflater)

        initViewModels()
        initObeserver()
        initListener()

        return binding.root
    }
    private fun initListener(){
        binding.btnAddProduct.setOnClickListener {
            val newProduct = Product(
                binding.edtId.text.toString().toInt(),
                binding.edtTitle.text.toString(),
                binding.edtPrice.text.toString().toInt()
            )
            productViewModel.fetchProducts()

            if (onAddProductListener != null){
                onAddProductListener!!.onAddProduct(newProduct)
            }
            parentFragmentManager.popBackStack()
        }
    }
    private fun initObeserver(){
        productViewModel.productMutableLiveData.observe(
            viewLifecycleOwner
        ){
            if (it){
                parentFragmentManager.popBackStack()
            }
        }
    }
    private fun initViewModels(){
        productViewModel = ViewModelProvider(
            this,
            ProductViewModelFactory(
                ProductRepository(
                    EcomDatabase.getInstance(requireContext()).getProductDao()
                )
            )
        )[ProductViewModel::class.java]
    }
}