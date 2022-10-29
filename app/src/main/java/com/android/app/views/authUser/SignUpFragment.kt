package com.android.app.views.authUser

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.app.R
import com.android.app.databinding.FragmentSignUpBinding
import com.android.app.util.HashUtils
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class SignUpFragment : Fragment(), HashUtils {

    private val viewModel: SignUpViewModel by viewModels()

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private lateinit var oldColors: ColorStateList

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sign_up, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        initViews()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.signUpDomain.observe(viewLifecycleOwner) { dto ->
            when (dto?.statusCode) {
                201 -> {
                    clearViews()
                    Toast.makeText(
                        context,
                        getString(R.string.sign_up_success, dto.email),
                        Toast.LENGTH_LONG
                    ).show()
                }
                400 -> {
                    Toast.makeText(
                        context,
                        getString(R.string.sign_up_bad_request, dto.email),
                        Toast.LENGTH_LONG
                    ).show()
                    initEmailListener()
                }
                else -> {
                    Toast.makeText(context, getString(R.string.sign_up_error), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun initViews() {
        oldColors = binding.emailEt.textColors
        binding.signUpBtn.setOnClickListener {
            viewModel.signUpUser(
                binding.nameEt.text.toString(),
                binding.emailEt.text.toString(),
                binding.passwordEt.text.toString().sha256(),
                binding.isAdminCb.isChecked
            )
        }
    }

    private fun initEmailListener() {
        context?.let { ctx ->
            val resId = ContextCompat.getColor(ctx, R.color.design_default_color_error)
            binding.emailEt.setTextColor(resId)
        }

        binding.signUpBtn.isEnabled = false

        binding.emailEt.doOnTextChanged { text, start, before, count ->
            binding.emailEt.setTextColor(oldColors)
            binding.signUpBtn.isEnabled = true
        }
    }

    private fun clearViews() {
        binding.nameEt.text.clear()
        binding.emailEt.text.clear()
        binding.passwordEt.text.clear()
        binding.isAdminCb.isChecked = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}