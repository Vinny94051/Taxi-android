package com.example.taximuslim.presentation.view.auth.driver.validatePerson

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
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
import com.example.taximuslim.databinding.AuthDriverValidatePersonFragmentBinding
import com.example.taximuslim.domain.models.driver.auth.DriverMainModel
import com.example.taximuslim.presentation.view.auth.driver.documents.AuthDriverDocumentsFragment
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_auth_driver_main.*

class AuthDriverValidatePersonFragment : ObservableFragment() {

    private lateinit var viewModel: AuthDriverValidatePersonViewModel
    private lateinit var driverModel: DriverMainModel

    companion object{
        private const val PROFILE_IMAGE_REQUEST = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        val binding = AuthDriverValidatePersonFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverValidatePersonViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        driverModel = AuthDriverValidatePersonFragmentArgs.fromBundle(arguments!!)
            .driverModel
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
        viewModel.navigate.observe(viewLifecycleOwner, Observer{navigate ->
            if (navigate){
                val navController = view!!.findNavController()
                navController.navigate(R.id.action_authDriverValidatePersonFragment_to_driverAuthRuleFragment)
                viewModel.onNavigate()
            }
        })
        viewModel.takePhoto.observe(viewLifecycleOwner, Observer{
            if (it) {
                viewModel.takePhoto.value = false
                ImagePicker.with(this)
                    .start(PROFILE_IMAGE_REQUEST)
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
                PROFILE_IMAGE_REQUEST -> {
                    val uri = ImagePicker.getFile(data)!!.toUri()
                    viewModel.profileImage.value = uri
                    viewModel.uploadProfilePhoto()
                }
            }
        }
    }
}
