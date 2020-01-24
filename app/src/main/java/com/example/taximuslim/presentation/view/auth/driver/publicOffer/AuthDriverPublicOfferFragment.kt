package com.example.taximuslim.presentation.view.auth.driver.publicOffer

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.taximuslim.R

class AuthDriverPublicOfferFragment : Fragment() {

    companion object {
        fun newInstance() = AuthDriverPublicOfferFragment()
    }

    private lateinit var viewModel: AuthDriverPublicOfferViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.show()
        viewModel = ViewModelProviders.of(this).get(AuthDriverPublicOfferViewModel::class.java)
        return inflater.inflate(R.layout.auth_driver_public_offer_fragment, container, false)
    }

}
