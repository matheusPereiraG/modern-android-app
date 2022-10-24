package com.android.app.views.authUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.android.app.R
import com.android.app.databinding.FragmentSignUpBinding
import com.android.app.network.model.NetworkSignUpResponse
import com.android.app.util.HashUtils
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import timber.log.Timber

@AndroidEntryPoint
class SignUpFragment : Fragment(), HashUtils {

    private val viewModel: SignUpViewModel by viewModels()
    //private val args: UserDetailsFragmentArgs by navArgs()

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sign_up, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.signUpBtn.setOnClickListener {
            viewModel.signUpUser(
                binding.nameEt.text.toString(),
                binding.emailEt.text.toString(),
                binding.passwordEt.toString().sha256(),
                binding.isAdminCb.isChecked
            )
        }

        viewModel.message.observe(viewLifecycleOwner) { message ->
            message?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.refreshUserDetails(args.user)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserDetails(args.user).observe(viewLifecycleOwner, {
            viewModel.userDetails.set(it)
        })
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}