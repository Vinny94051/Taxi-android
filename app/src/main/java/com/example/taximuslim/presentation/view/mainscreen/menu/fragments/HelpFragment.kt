package com.example.taximuslim.presentation.view.mainscreen.menu.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.presentation.view.auth.fragments.base.IInitViews
import com.example.taximuslim.presentation.view.mainscreen.menu.MenuItemsActivity
import com.example.taximuslim.utils.permissions.PermissionManager
import kotlinx.android.synthetic.main.fragment_help.*
import android.content.ActivityNotFoundException
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import com.example.taximuslim.utils.toEditable
import java.text.SimpleDateFormat
import java.util.*


class HelpFragment : BaseFragment(), View.OnClickListener, IInitViews {
    companion object {
        const val ID = "HELP_FRAGMENT"
        val INSTANCE = HelpFragment()
    }


    private lateinit var permissionManager: PermissionManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        permissionManager = PermissionManager(context as AppCompatActivity)
        permissionManager.checkCallPermission()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.sent_letter_button -> {
                val mailto = "mailto:" + mail_text.text +
                        "?cc=" + mail_text.text +
                        "&subject=" + Uri.encode(theme_input.text.toString()) +
                        "&body=" + Uri.encode(text_input.text.toString())

                val emailIntent = Intent(Intent.ACTION_SENDTO)
                emailIntent.data = Uri.parse(mailto)
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send Email"))
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }

            }
            R.id.enter_button -> {
                val intent =
                    Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+749597556983"))
                startActivity(intent)
            }
        }
    }

    override fun initViews() {
        sent_letter_button.setOnClickListener(this)
        enter_button.setOnClickListener(this)
        theme_input.text = getCurrentDate().toEditable()
    }


    override fun layoutId() = R.layout.fragment_help
    override fun buttonText(): String = resources.getString(R.string.call_us)

    private fun getCurrentDate(): String {
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd MMMM HH:mm", Locale.getDefault())
        return formatter.format(date)
    }
}