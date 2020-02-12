package com.example.taximuslim.presentation.view.mainscreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import com.example.taximuslim.utils.permissions.PermissionManager
import kotlinx.android.synthetic.main.fragment_driver_wait.*

class DriverWaitFragment : BaseFragment() {

    companion object : BaseFragmentCompanion() {
        override val ID: String
            get() = "DRIVER_WAIT_FRAGMENT"

        override fun newInstance() = DriverWaitFragment()
    }

    lateinit var statusAndDrivers: StatusAndDrivers
    lateinit var owner: MapsActivity
    lateinit var permissionManager: PermissionManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as MapsActivity
        permissionManager = PermissionManager(owner)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeACallButton.setOnClickListener {
            makeACall()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        carTextView.text = "Ожидает " + statusAndDrivers.car
        pointAAddressTextView.text = statusAndDrivers.startPointAddress
        pointBAddressTextView.text = statusAndDrivers.endPointAddress
    }


    override fun layoutId(): Int = R.layout.fragment_driver_wait

    var counter = 0
    private fun makeACall() {


        when (permissionManager.checkCallPermission()) {
            true -> {
                val intent =
                    Intent(Intent.ACTION_CALL, Uri.parse("tel:" + statusAndDrivers.driverPhone))
                startActivity(intent)
            }
            false -> {
                if (counter == 0) {
                    makeACall()
                    @Suppress("UNUSED_CHANGED_VALUE")
                    counter++
                }
            }
        }
    }
}