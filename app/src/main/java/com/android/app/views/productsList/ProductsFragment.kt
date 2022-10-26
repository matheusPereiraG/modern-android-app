package com.android.app.views.productsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.app.R
import com.android.app.databinding.FragmentProductsListBinding
import com.android.app.network.model.Product
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
        binding.productsRv.adapter = adapter
        adapter.setData(
            listOf(
                Product("ehhh", "Nome 1", 12, "Description", 13)
            )
        )
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}