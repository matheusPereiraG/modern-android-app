package com.android.app.views.authUser

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
import com.android.app.databinding.FragmentLogInBinding
import com.android.app.util.HashUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : Fragment(), HashUtils {

    private val viewModel: LoginInViewModel by viewModels()
    //private val args: SignUpFragmentArgs by navArgs()

    private var _binding: FragmentLogInBinding? = null
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
            inflater, R.layout.fragment_log_in, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        initViews()

        return binding.root
    }

    private fun initViews() {
        binding.goToSignUpBtn.setOnClickListener {
            findNavController().navigate(LogInFragmentDirections.actionLogInToSignUp())
        }

        binding.logInBtn.setOnClickListener {
            binding.logInBtn.isEnabled = false
            viewModel.logIn(
                binding.emailEt.text.toString(),
                binding.passwordEt.text.toString().sha256()
            )
        }

        binding.logInBtn.isEnabled = false
        binding.emailEt.doOnTextChanged { text, _, _, _ ->
            val currTextLength = text?.length ?: 0
            binding.logInBtn.isEnabled = currTextLength > 0 && binding.passwordEt.text.isNotEmpty()
        }

        binding.passwordEt.doOnTextChanged { text, _, _, _ ->
            val currTextLength = text?.length ?: 0
            binding.logInBtn.isEnabled = currTextLength > 0 && binding.emailEt.text.isNotEmpty()
        }
    }

    private fun initObservers() {
        viewModel.logInDomain.observe(this) { dto ->
            binding.logInBtn.isEnabled = true
            when (dto?.statusCode) {
                200 -> {
                    Toast.makeText(
                        context,
                        dto.message,
                        Toast.LENGTH_LONG
                    ).show()
                    findNavController().navigate(LogInFragmentDirections.actionLogInToProducts())
                }
                400 -> {
                    var errorMsg = getString(R.string.sign_up_error)
                    dto.message?.let {
                        errorMsg = it
                    }
                    Toast.makeText(
                        context,
                        errorMsg,
                        Toast.LENGTH_LONG
                    ).show()

                    binding.logInBtn.isEnabled = false
                }
                else -> {
                    Toast.makeText(context, getString(R.string.sign_up_error), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}