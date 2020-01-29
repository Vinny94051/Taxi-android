package com.example.taximuslim.presentation.view.auth.driver.publicOffer

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.taximuslim.R
import kotlinx.android.synthetic.main.activity_auth_driver_main.*
import kotlinx.android.synthetic.main.auth_driver_public_offer_fragment.*

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
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        viewModel = ViewModelProviders.of(this).get(AuthDriverPublicOfferViewModel::class.java)
        return inflater.inflate(R.layout.auth_driver_public_offer_fragment, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://23.105.226.153:1111/licensedAgreement.html")
    }
}
