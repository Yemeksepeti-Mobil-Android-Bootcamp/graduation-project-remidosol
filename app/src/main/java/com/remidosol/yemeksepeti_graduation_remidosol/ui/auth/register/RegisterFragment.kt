package com.remidosol.yemeksepeti_graduation_remidosol.ui.auth.register

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.remidosol.yemeksepeti_graduation_remidosol.R
import com.remidosol.yemeksepeti_graduation_remidosol.databinding.RegisterFragmentBinding
import com.remidosol.yemeksepeti_graduation_remidosol.utils.InputStreamRequestBody
import com.remidosol.yemeksepeti_graduation_remidosol.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var _binding: RegisterFragmentBinding

    private lateinit var fileUri: Uri

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  RegisterFragmentBinding.inflate(inflater, container, false)

        initListeners(savedInstanceState!!)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun pickImageFromGallery() {
        val intent: Intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(_binding.root.context, "Permission Denied", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val uri: Uri = data?.data!!

            fileUri = uri

            _binding.uploadImage.setImageURI(uri)

        } else if (resultCode == AppCompatActivity.RESULT_CANCELED && requestCode == IMAGE_PICK_CODE) {
            Toast.makeText(_binding.root.context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(_binding.root.context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListeners(bundle: Bundle){
        _binding.uploadImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(
                        _binding.root.context,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == android.content.pm.PackageManager.PERMISSION_DENIED
                ) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    pickImageFromGallery()
                }
            } else {
                pickImageFromGallery()
            }
        }
        _binding.signUpAppcompatButton.setOnClickListener {

            val email = _binding.emailEditText.text.toString()
            val password = _binding.passwordEditText.text.toString()
            val firstName = _binding.firstNameEditText.text.toString()
            val lastName = _binding.lastNameEditText.text.toString()
            val mobileNumber = _binding.mobileNumberEditText.text.toString()

            val avatarUrl =
                InputStreamRequestBody.getFileName(_binding.uploadImage.context, fileUri)

            val registerBody = HashMap<String, RequestBody>()

            registerBody["email"] = email.toRequestBody()
            registerBody["password"] = password.toRequestBody()
            registerBody["firstName"] = firstName.toRequestBody()
            registerBody["lastName"] = lastName.toRequestBody()
            registerBody["email"] = email.toRequestBody()
            registerBody["mobileNumber"] = mobileNumber.toRequestBody()
            registerBody["avatarUrl\"; filename=\"${avatarUrl}\""] = avatarUrl!!.toRequestBody()

            val buttonBitmap: Bitmap =
                BitmapFactory.decodeResource(_binding.root.resources, R.drawable.ic_done)

            viewModel.register(registerBody)
                .observe(viewLifecycleOwner, {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            _binding.signUpAppcompatButton.startAnimation()
                        }
                        Resource.Status.SUCCESS -> {
                            _binding.signUpAppcompatButton.doneLoadingAnimation(R.color.dark, buttonBitmap)
                            bundle.putParcelable("user", it.data!!.data!!.user)
                            findNavController().navigate(
                                R.id.action_loginFragment_to_homeFragment,
                                bundle
                            )
                        }
                        Resource.Status.ERROR -> {
                            _binding.signUpAppcompatButton.revertAnimation()
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

}