package com.android.app.views.createProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.app.R
import com.android.app.databinding.FragmentCreateProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateProductFragment : Fragment() {

    private val viewModel: CreateProductViewModel by viewModels()

    private var _binding: FragmentCreateProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_product, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        initViews()
        initObservers()
        return binding.root
    }

    private fun initViews() {

    }

    private fun initObservers() {
        viewModel.createProduct.observe(viewLifecycleOwner) {
            //adapter.setData(it)
        }
    }
}