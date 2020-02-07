package com.example.taximuslim.presentation.view.auth.driver.documents

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.example.taximuslim.R
import com.example.taximuslim.databinding.AuthDriverCarPhotoFragmentBinding
import com.example.taximuslim.databinding.AuthDriverDocumentsFragmentBinding
import com.example.taximuslim.domain.models.driver.auth.DriverMainModel
import com.example.taximuslim.presentation.view.auth.driver.carPhoto.AuthDriverCarPhoto
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_auth_driver_main.*

class AuthDriverDocumentsFragment : ObservableFragment() {

    private lateinit var viewModel: AuthDriverDocumentsViewModel
    private lateinit var driverModel: DriverMainModel
    companion object{
        private const val DRIVER_FRONT_REQUEST = 0
        private const val DRIVER_BACK_REQUEST = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        val binding = AuthDriverDocumentsFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverDocumentsViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        driverModel = AuthDriverDocumentsFragmentArgs.fromBundle(arguments!!).driverModel
        viewModel.initVIewModel(driverModel)
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
        viewModel.navigate.observe(viewLifecycleOwner, Observer{navigate ->
            if (navigate){
                val navController = view!!.findNavController()

                driverModel.driverLicenceNumb = viewModel.driverLicenceNumb.value!!
                driverModel.driverLicenceFront = viewModel.driverLicenceFront.value!!
                driverModel.driverLicenceBack = viewModel.driverLicenceBack.value!!

                navController.navigate(AuthDriverDocumentsFragmentDirections
                    .actionAuthDriverDocumentsFragmentToAuthDriverValidatePersonFragment(driverModel))
                viewModel.onNavigate()
            }

        })
        viewModel.takeLicenceFront.observe(viewLifecycleOwner, Observer{
            if (it){
                viewModel.takeLicenceFront.value = false
                ImagePicker.with(this)
                    .start(DRIVER_FRONT_REQUEST)
            }
        })
        viewModel.takeLicenceBack.observe(viewLifecycleOwner, Observer{
            if (it){
                viewModel.takeLicenceBack.value = false
                ImagePicker.with(this)
                    .start(DRIVER_BACK_REQUEST)
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer{
            if (it == true){
                Toast.makeText(context, getString(R.string.load_all_data), Toast.LENGTH_SHORT).show()
                viewModel.error.value = false
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                DRIVER_FRONT_REQUEST -> {
                    val uri = ImagePicker.getFile(data)!!.toUri()
                    viewModel.driverLicenceFront.value = uri
                    viewModel.uploadDriverLicenceFront()
                }
                DRIVER_BACK_REQUEST ->{
                    val uri = ImagePicker.getFile(data)!!.toUri()
                    viewModel.driverLicenceBack.value = uri
                    viewModel.uploadDriverLicenceBack()
                }
            }
        }
    }
}
