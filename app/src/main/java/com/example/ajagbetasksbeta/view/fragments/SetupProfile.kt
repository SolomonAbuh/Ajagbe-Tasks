package com.example.ajagbetasksbeta.view.fragments

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.ajagbetasksbeta.R
import com.example.ajagbetasksbeta.databinding.FragmentSetupProfileBinding
import com.example.ajagbetasksbeta.databinding.SelectImageDialogBinding

class SetupProfile : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSetupProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSetupProfileBinding.inflate(layoutInflater)

        binding.editDp.setOnClickListener(this)
        binding.setupDoneBtn.setOnClickListener(this)

        Glide.with(this).load(R.drawable.ic_user).circleCrop().into(binding.displayPicture)

        return binding.root
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.edit_dp -> {
                addImageDialog()
            }
            R.id.setup_done_btn -> {
                if (binding.usernameEt.text!!.isEmpty()) {
                    binding.usernameEtLayout.error = "Username can't be empty"
                    YoYo.with(Techniques.Shake)
                        .duration(700)
                        .repeat(3)
                        .playOn(binding.usernameEt);
                } else {
                    binding.usernameEtLayout.error = null
                    findNavController().navigate(R.id.homeFragment)
                }
            }
        }
    }

    private fun addImageDialog() {
        val dialog = Dialog(requireActivity())
        val binding: SelectImageDialogBinding = SelectImageDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()

        requestAccess()

        binding.selectCamera.setOnClickListener {
            dialog.dismiss()
            openCamera()
        }

        binding.selectGallery.setOnClickListener {
            dialog.dismiss()
            openGallery()
        }
    }

    private fun cameraPermission() =
        ActivityCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

    private fun readExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED


    private fun openCamera() {
        if (cameraPermission()) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA)
        }
    }

    private fun openGallery() {
        if (readExternalStoragePermission()) {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, GALLERY)
        }
    }

    private fun requestAccess() {
        val permissionsToRequest = mutableListOf<String>()
        if (!readExternalStoragePermission()) {
            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (!cameraPermission()) {
            permissionsToRequest.add(Manifest.permission.CAMERA)
        }
        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissionsToRequest.toTypedArray(),
                0
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA) {
                val thumbnail: Bitmap = data?.extras!!.get("data") as Bitmap
                Glide.with(this)
                    .load(thumbnail)
                    .circleCrop()
                    .into(binding.displayPicture)
            }
            if (requestCode == GALLERY) {
                val selectedPhotoUri = data!!.data
                Glide.with(this)
                    .load(selectedPhotoUri)
                    .circleCrop()
                    .into(binding.displayPicture)
            }
        }
    }

    companion object {
        private const val CAMERA = 1
        private const val GALLERY = 2
    }

}