package com.example.taximuslim.presentation.view.auth.driver.carPhoto

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.taximuslim.R
import com.example.taximuslim.databinding.AuthDriverCarPhotoFragmentBinding
import com.example.taximuslim.domain.models.driver.auth.DriverMainModel
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_auth_driver_main.*

class AuthDriverCarPhoto : ObservableFragment() {

    companion object {
        private const val CAR_IMAGE_REQUEST = 3
        private const val CERTIFICATE_IMAGE_REQUEST = 4
    }

    private lateinit var viewModel: AuthDriverCarPhotoViewModel
    private lateinit var binding: AuthDriverCarPhotoFragmentBinding
    private lateinit var driverModel: DriverMainModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        binding = AuthDriverCarPhotoFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverCarPhotoViewModel::class.java)
        driverModel = AuthDriverCarPhotoArgs.fromBundle(arguments!!).driverModel

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.driverModel = driverModel

        viewModel.initViewModel(driverModel)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonText: TextView? = view.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.continuee)
        }
    }

    override fun setObservers() {
        viewModel.navigateToNext.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                val navController = binding.root.findNavController()
                driverModel.carImage = viewModel.carImage.value
                driverModel.carCertificateImage = viewModel.certificateImage.value
                navController.navigate(
                    AuthDriverCarPhotoDirections
                        .actionAuthDriverCarPhotoToAuthDriverAboutYouFragment(driverModel)
                )
                viewModel.onNavigate()
            }
        })
        viewModel.takeCarPhoto.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.takeCarPhoto.value = false
                ImagePicker.with(this)
                    .start(CAR_IMAGE_REQUEST)
            }
        })
        viewModel.takeCertificatePhoto.observe(viewLifecycleOwner, Observer{
            if (it){
                viewModel.takeCertificatePhoto.value = false
                ImagePicker.with(this)
                    .start(CERTIFICATE_IMAGE_REQUEST)
            }
        })
        viewModel.showToast.observe(viewLifecycleOwner, Observer{
            if (it == true){
                Toast.makeText(context, getString(R.string.load_all_data), Toast.LENGTH_SHORT).show()
                viewModel.showToast.value = false
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAR_IMAGE_REQUEST -> {
                    val uri = ImagePicker.getFile(data)!!.toUri()
                    viewModel.carImage.value = uri
                    viewModel.uploadCarImage()
                }
                CERTIFICATE_IMAGE_REQUEST ->{
                    val uri = ImagePicker.getFile(data)!!.toUri()
                    viewModel.certificateImage.value = uri
                    viewModel.uploadCertificateImage()
                }
            }
        }
    }
}
