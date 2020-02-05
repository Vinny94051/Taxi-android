package com.example.taximuslim.presentation.view.auth.driver.aboutYou

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
import com.example.taximuslim.databinding.AuthDriverAboutYouFragmentBinding
import com.example.taximuslim.domain.models.driver.auth.DriverMainModel
import com.example.taximuslim.presentation.view.auth.driver.carPhoto.AuthDriverCarPhoto
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_auth_driver_main.*

class AuthDriverAboutYouFragment : Fragment() {

    private lateinit var viewModel: AuthDriverAboutYouViewModel
    private lateinit var driverModel: DriverMainModel

    companion object{
        private const val PROFILE_IMAGE_CODE = 3
        private const val LICENCE_IMAGE_FRONT_CODE = 4
        private const val LICENCE_IMAGE_BACK_CODE = 5
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        val binding = AuthDriverAboutYouFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverAboutYouViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        driverModel = AuthDriverAboutYouFragmentArgs.fromBundle(arguments!!)
            .driverModel

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonText: TextView? = view.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.continuee)
        }
    }

    private fun setObservers(){
        viewModel.navigate.observe(viewLifecycleOwner, Observer{navigate ->
            if (navigate){
                val navController = view!!.findNavController()
                driverModel.profileImage = viewModel.profileImage.value!!
                driverModel.profileName = viewModel.profileName.value!!
                driverModel.profileSurname= viewModel.profileSurname.value!!
                driverModel.profileEmail = viewModel.profileEmail.value!!
                driverModel.taxiLicenceFront = viewModel.taxiLicenceFront.value!!
                driverModel.driverLicenceBack = viewModel.taxiLicenceBack.value!!
                navController.navigate(AuthDriverAboutYouFragmentDirections
                    .actionAuthDriverAboutYouFragmentToAuthDriverDocumentsFragment(driverModel))
                viewModel.onNavigate()
            }
        })
        viewModel.takeProfileImage.observe(viewLifecycleOwner, Observer{
            if (it){
                viewModel.takeProfileImage.value = false
                ImagePicker.with(this)
                    .start(PROFILE_IMAGE_CODE)
            }
        })
        viewModel.takeLicenceFrontImage.observe(viewLifecycleOwner, Observer{
            if (it){
                viewModel.takeLicenceFrontImage.value = false
                ImagePicker.with(this)
                    .start(LICENCE_IMAGE_FRONT_CODE)
            }
        })
        viewModel.takeLicenceBackImage.observe(viewLifecycleOwner, Observer{
            if (it){
                viewModel.takeLicenceBackImage.value = false
                ImagePicker.with(this)
                    .start(LICENCE_IMAGE_BACK_CODE)
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer{
            if (it){
                Toast.makeText(context, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
                viewModel.error.value = false
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                PROFILE_IMAGE_CODE -> {
                    val uri = ImagePicker.getFile(data)!!.toUri()
                    viewModel.profileImage.value = uri
                    viewModel.uploadProfileImage()
                }
                LICENCE_IMAGE_FRONT_CODE ->{
                    val uri = ImagePicker.getFile(data)!!.toUri()
                    viewModel.taxiLicenceFront.value = uri
                    viewModel.uploadLicenceFront()
                }
                LICENCE_IMAGE_BACK_CODE->{
                    val uri = ImagePicker.getFile(data)!!.toUri()
                    viewModel.taxiLicenceBack.value = uri
                    viewModel.uploadLicenceBack()
                }
            }
        }
    }
}
