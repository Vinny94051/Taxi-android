package com.example.taximuslim.presentation.view.driver.becamePassenger


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.splashscreen.SplashScreenActivity

/**
 * A simple [Fragment] subclass.
 */
class BecamePassengerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val intent = Intent(context, SplashScreenActivity::class.java)
        startActivity(intent)
        activity!!.finish()
        return TextView(activity)
    }


}
