package com.example.taximuslim.presentation.view.driver.settings.changeNumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.taximuslim.R
import com.example.taximuslim.databinding.ChangeNumbFragmentBinding
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import kotlinx.android.synthetic.main.activity_auth_driver_main.*

class ChangeNumbFragment : ObservableFragment() {

    private lateinit var viewModel: ChangeNumbViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        (activity as AppCompatActivity).supportActionBar?.show()
        viewModel = ViewModelProviders.of(this).get(ChangeNumbViewModel::class.java)
        val binding = ChangeNumbFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun setObservers() {
        viewModel.navigate.observe(viewLifecycleOwner, Observer {
            view?.findNavController()
                ?.navigate(R.id.action_changeNumbFragment_to_validateChangeNumbCodeFragment)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonText: TextView? = view.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.continuee)
        }
    }
}
