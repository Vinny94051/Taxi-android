package com.example.taximuslim.presentation.view.driver.settings.changeNumber

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.taximuslim.R
import com.example.taximuslim.databinding.ChangeNumbFragmentBinding
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import kotlinx.android.synthetic.main.activity_auth_driver_main.*

class ChangeNumbFragment : ObservableFragment() {

    private lateinit var viewModel: ChangeNumbViewModel
    private lateinit var binding: ChangeNumbFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        (activity as AppCompatActivity).supportActionBar?.hide()
        viewModel = ViewModelProvider(this).get(ChangeNumbViewModel::class.java)
        binding = ChangeNumbFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun setListeners() {
        binding.editNumb.setOnFocusChangeListener{_, focused ->
            if (focused){
                viewModel.focused()
            }else{
                viewModel.unfocused()
            }
        }
        binding.editNumb.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                viewModel.onChange()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun setObservers() {
        viewModel.navigate.observe(viewLifecycleOwner, Observer {navigate ->
            navigate?.let{
                if (navigate){
                    viewModel.onNavigate()
                    view?.findNavController()
                        ?.navigate(R.id.action_changeNumbFragment_to_validateChangeNumbCodeFragment)

                }
            }
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
