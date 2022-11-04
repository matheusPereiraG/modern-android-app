package com.android.app.views.createProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.app.R
import com.android.app.databinding.FragmentCreateProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateProductFragment : Fragment() {

    private val viewModel: CreateProductViewModel by viewModels()

    private var _binding: FragmentCreateProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

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
        return binding.root
    }

    private fun initViews() {
        binding.prodNameEt.doOnTextChanged { _, _, _, _ ->
            binding.saveBtn.isEnabled = canSave()
        }

        binding.descriptionEt.doOnTextChanged { _, _, _, _ ->
            binding.saveBtn.isEnabled = canSave()
        }

        binding.priceEt.doOnTextChanged { _, _, _, _ ->
            binding.saveBtn.isEnabled = canSave()
        }

        binding.quantityEt.doOnTextChanged { _, _, _, _ ->
            binding.saveBtn.isEnabled = canSave()
        }

        binding.saveBtn.setOnClickListener {
            try {
                viewModel.createProduct(binding.prodNameEt.text.toString(),
                    binding.priceEt.text.toString().toInt(),
                    binding.descriptionEt.text.toString(),
                    binding.quantityEt.text.toString().toInt())
            } catch (e: java.lang.NumberFormatException) {
                e.printStackTrace()
            }
        }
    }

    private fun canSave(): Boolean {
        return binding.prodNameEt.text.isNotEmpty() && binding.descriptionEt.text.isNotEmpty()
                && binding.priceEt.text.isNotEmpty() && binding.quantityEt.text.isNotEmpty()

    }

    private fun initObservers() {
        viewModel.createProduct.observe(this) {
            Toast.makeText(context, "Nice! ${it.prodName} was saved", Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
            //findNavController().navigate(CreateProductFragmentDirections.actionPopBack())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}