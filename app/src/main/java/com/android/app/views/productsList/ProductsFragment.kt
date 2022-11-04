package com.android.app.views.productsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

    private var _binding: FragmentProductsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    activity?.finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_products_list, container, false
        )
        initViews()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllProducts()
    }

    private fun initObservers() {
        viewModel.productsList.observe(this) {
            adapter.setData(it)
        }
    }

    private fun initViews() {
        val layoutManager = LinearLayoutManager(context)
        binding.productsRv.layoutManager = layoutManager
        binding.productsRv.adapter = adapter
        binding.productsRv.addItemDecoration(
            DividerItemDecoration(
                context,
                layoutManager.orientation
            )
        )

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.create_product)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}