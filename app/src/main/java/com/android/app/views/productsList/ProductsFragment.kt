package com.android.app.views.productsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.app.R
import com.android.app.databinding.FragmentProductsListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModels()

    @Inject
    lateinit var adapter: ProductsAdapter
    //private val args: SignUpFragmentArgs by navArgs()

    private var _binding: FragmentProductsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_products_list, container, false
        )

        initRecyclerView()
        initObservers()

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getAllProducts()

        return binding.root
    }

    private fun initObservers() {
        viewModel.productsList.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.productsRv.layoutManager = layoutManager
        binding.productsRv.adapter = adapter
        binding.productsRv.addItemDecoration(
            DividerItemDecoration(
                context,
                layoutManager.orientation
            )
        )
    }
}