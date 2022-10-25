package com.android.app.views.authUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.app.R
import com.android.app.databinding.FragmentLogInBinding
import com.android.app.util.HashUtils

class LogInFragment : Fragment(), HashUtils {

    private val viewModel: LoginInViewModel by viewModels()
    //private val args: SignUpFragmentArgs by navArgs()

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

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
        initObservers()
        return binding.root
    }

    private fun initViews() {
        binding.signUpBtn.setOnClickListener {
            findNavController().navigate(LogInFragmentDirections.actionLogInToSignUp()/*UserListFragmentDirections.actionUsersListToUserDetails(it.username)*/)
        }

        binding.logInBtn.setOnClickListener {
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
        
        binding.passwordEt.doOnTextChanged{ text, _, _, _ ->
            val currTextLength = text?.length ?: 0
            binding.logInBtn.isEnabled = currTextLength > 0 && binding.emailEt.text.isNotEmpty()
        }
    }

    private fun initObservers() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}