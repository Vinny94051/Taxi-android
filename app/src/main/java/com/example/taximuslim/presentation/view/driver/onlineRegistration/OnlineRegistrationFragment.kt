package com.example.taximuslim.presentation.view.driver.onlineRegistration


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.driver.AuthDriverMainActivity

/**
 * A simple [Fragment] subclass.
 */
class OnlineRegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inteit = Intent(context, AuthDriverMainActivity::class.java)
        startActivity(inteit)
        activity!!.finish()
        return TextView(activity).apply {
            setText(R.string.hello_blank_fragment)
        }
    }


}
