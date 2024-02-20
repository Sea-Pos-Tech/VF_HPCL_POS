package com.hpcl_paytm.activity.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hpcl_paytm.R
import com.hpcl_paytm.activity.SettlementDialog
import com.hpcl_paytm.activity.constants.Constants
import com.hpcl_paytm.activity.constants.TransactionUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private var settlementDialog: SettlementDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        showDialog()

        CoroutineScope(Dispatchers.Main).launch{ // Checks for terminal id is null navigating to Terminal Id Screen
            delay(1000)
            if (TransactionUtils.Companion.isTerminalRegistered(
                    this@SplashActivity,
                    Constants.REGISTRATION_STATUS
                )
            ) {
                settlementDialog!!.dismiss()
                val i = Intent(
                    this@SplashActivity,
                    MainActivity::class.java
                )
                startActivity(i)
                finish()
                overridePendingTransition(0, 0)
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            } else {
                settlementDialog!!.dismiss()
                val i = Intent(
                    this@SplashActivity,
                    TerminalIdActivity::class.java
                )
                startActivity(i)
                finish()
            }
        }
    }

    fun showDialog() {
        settlementDialog = SettlementDialog(this)
        settlementDialog?.setTitle("Please Wait....")
        settlementDialog?.imageView?.visibility = View.GONE
        settlementDialog?.processing?.text = "Application is Getting Ready..."
        settlementDialog?.packetStatus?.visibility = View.GONE
        settlementDialog?.timerAndEndpoint?.visibility = View.GONE
        settlementDialog?.show()
    }
}