package com.bitcodetech.assignment_roomdatabasemvvm.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitcodetech.assignment_roomdatabasemvvm.R
import com.bitcodetech.assignment_roomdatabasemvvm.adapter.ProductAdapter
import com.bitcodetech.assignment_roomdatabasemvvm.dao.ProductDao
import com.bitcodetech.assignment_roomdatabasemvvm.database.EcomDatabase
import com.bitcodetech.assignment_roomdatabasemvvm.databinding.ProductFragmentBinding
import com.bitcodetech.assignment_roomdatabasemvvm.entity.Product
import com.bitcodetech.assignment_roomdatabasemvvm.factory.ProductViewModelFactory
import com.bitcodetech.assignment_roomdatabasemvvm.repository.ProductRepository
import com.bitcodetech.assignment_roomdatabasemvvm.viewmodels.ProductViewModel

class ProductFragment :Fragment() {
    private lateinit var binding : ProductFragmentBinding
    private lateinit var productViewModel: ProductViewModel
   private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductFragmentBinding.inflate(layoutInflater)

        initViews()
        initViewModel()
        initObserver()
       initAdapter()
        initListener()

        productViewModel.fetchProducts()
        return binding.root
    }
    private fun initListener(){
        binding.btnAddProducts.setOnClickListener {
            val addProductFragment = AddProductFragment()

            addProductFragment.onAddProductListener =
                object : AddProductFragment.OnAddProductListener{
                    override fun onAddProduct(product: Product) {
                        productViewModel.products.add(product)
                        productAdapter.notifyItemInserted(productViewModel.products.size-1)
                    }
                }
            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer,addProductFragment,null)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initAdapter(){
        productAdapter = ProductAdapter(productViewModel.products)
        binding.recyclerProducts.adapter = productAdapter
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver(){
        productViewModel.productMutableLiveData.observe(
            viewLifecycleOwner
        ){
            if (it){
                productAdapter.notifyDataSetChanged()
            }
        }
    }
    private fun initViewModel(){
        productViewModel = ViewModelProvider(
            this,
            ProductViewModelFactory(
                ProductRepository(
                    EcomDatabase.getInstance(requireContext()).getProductDao()
                )
            )
        )[ProductViewModel::class.java]
    }
    private fun initViews(){
        binding.recyclerProducts.layoutManager =
            LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

    }
}