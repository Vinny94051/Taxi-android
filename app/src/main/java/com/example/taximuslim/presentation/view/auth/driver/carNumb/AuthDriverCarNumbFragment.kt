package com.example.taximuslim.presentation.view.auth.driver.carNumb

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import kotlinx.android.synthetic.main.auth_driver_car_numb_fragment.*

class AuthDriverCarNumbFragment : BaseFragment() {

    override fun layoutId(): Int  = R.layout.auth_driver_car_numb_fragment

    private lateinit var viewModel: AuthDriverCarNumbViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthDriverCarNumbViewModel::class.java)
        setObservers()
        setListeners()
    }

    private fun setListeners(){
        setMainButtonListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonText: TextView? = view?.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.continuee)
        }
    }


    private fun setMainButtonListener(){
        mainButton.setOnClickListener {
            viewModel.writeCarNumb()
        }
    }

    private fun setObservers(){
        viewModel.navigateToNext.observe(viewLifecycleOwner, Observer{navigate->
            if (navigate){
                val navController = view!!.findNavController()
                navController.navigate(R.id.action_authDriverCarNumbFragment_to_authDriverCarPhoto)
                viewModel.onNavigateNext()
            }
        })
    }

}
