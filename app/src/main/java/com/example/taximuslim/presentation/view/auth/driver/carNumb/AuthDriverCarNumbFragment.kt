package com.example.taximuslim.presentation.view.auth.driver.carNumb

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.taximuslim.R
import com.example.taximuslim.databinding.AuthDriverCarNumbFragmentBinding
import com.example.taximuslim.domain.models.driver.auth.DriverMainModel
import com.example.taximuslim.presentation.view.auth.driver.carPhoto.AuthDriverCarPhotoArgs
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import kotlinx.android.synthetic.main.activity_auth_driver_main.*
import kotlinx.android.synthetic.main.auth_driver_car_numb_fragment.*

class AuthDriverCarNumbFragment : ObservableFragment() {


    private lateinit var viewModel: AuthDriverCarNumbViewModel
    private lateinit var driverModel: DriverMainModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        viewModel = ViewModelProviders.of(this).get(AuthDriverCarNumbViewModel::class.java)
        val binding = AuthDriverCarNumbFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        driverModel = AuthDriverCarNumbFragmentArgs.fromBundle(arguments!!)
            .driverModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObservers()
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
                driverModel.carNumb = viewModel.carNumb.value!!
                val navController = view!!.findNavController()
                navController.navigate(AuthDriverCarNumbFragmentDirections
                    .actionAuthDriverCarNumbFragmentToAuthDriverCarPhoto(driverModel))
                viewModel.onNavigate()
            }
        })
    }

    override fun setListeners() {
        carNumb.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                viewModel.onNumbChange()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
