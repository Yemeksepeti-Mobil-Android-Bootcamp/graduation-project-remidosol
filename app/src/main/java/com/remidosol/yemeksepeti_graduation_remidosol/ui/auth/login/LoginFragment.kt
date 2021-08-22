package com.remidosol.yemeksepeti_graduation_remidosol.ui.auth.login

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.remidosol.yemeksepeti_graduation_remidosol.R
import com.remidosol.yemeksepeti_graduation_remidosol.databinding.LoginFragmentBinding
import com.remidosol.yemeksepeti_graduation_remidosol.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)

        setListeners(savedInstanceState ?: bundleOf())

        return binding.root
    }

    private fun setListeners(bundle: Bundle) {
        val registerTextView: TextView = binding.root.findViewById(R.id.registerTextView)
        val signInAppcompatButton: CircularProgressButton =
            binding.root.findViewById(R.id.signInAppcompatButton)

        registerTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        signInAppcompatButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val buttonBitmap: Bitmap =
                BitmapFactory.decodeResource(binding.root.resources, R.drawable.ic_done)
            viewModel.login(email, password).observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        signInAppcompatButton.startAnimation()
                    }
                    Resource.Status.SUCCESS -> {
                        signInAppcompatButton.doneLoadingAnimation(R.color.dark, buttonBitmap)
                        bundle.putParcelable("user", it.data!!.data!!.user)
                        viewModel.saveUser(it.data.data!!.user)
                        findNavController().navigate(
                            R.id.action_loginFragment_to_homeFragment,
                            bundle
                        )
                    }
                    Resource.Status.ERROR -> {
                        signInAppcompatButton.revertAnimation()
                        val dialog = AlertDialog.Builder(context)
                            .setTitle("Error")
                            .setMessage("${it.message}")
                            .setPositiveButton("ok") { dialog, button ->
                                dialog.dismiss()
                            }
                        dialog.show()
                    }

                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}