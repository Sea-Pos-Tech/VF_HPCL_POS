package com.hpcl_paytm.activity.home

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hpcl_paytm.R
import com.hpcl_paytm.activity.SettlementDialog
import com.hpcl_paytm.activity.constants.GlobalMethods
import com.hpcl_paytm.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var navController: NavController? = null
    private var settlementDialog: SettlementDialog? = null
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        try {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } catch (e: Exception) {
            Log.e("error in wakeup", "" + e)
        }

        //  Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, 120000);
        showDialog()
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.colorAccent)
            navController = Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment)
            navController?.setGraph(R.navigation.nav_graph)
            settlementDialog?.dismiss()
            binding.imageViewSplash.visibility = View.GONE
        }

    }

    //create Show Dialog
    private fun showDialog() {
        settlementDialog = SettlementDialog(this)
        settlementDialog!!.setTitle("Please Wait.")
        settlementDialog?.imageView?.visibility = View.GONE
        settlementDialog?.processing?.text = "Application is Getting Ready..."
        settlementDialog!!.packetStatus.visibility = View.GONE
        settlementDialog!!.timerAndEndpoint.visibility = View.GONE
        settlementDialog!!.show()
        binding.imageViewSplash.visibility = View.VISIBLE
    }

    //Override on pause method
    override fun onPause() {
        super.onPause()
        if (!GlobalMethods.getIsKeyExchange(this)) {
         //   val i = Intent(this, KeyExchangeActivity::class.java)
          //  startActivity(i)
        } else {
            /* val activityManager = applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
              activityManager.moveTaskToFront(taskId, 0)*/
            //linearLayout?.setBackgroundDrawable(this.resources.getDrawable(R.drawable.back_drawable))
        }
    }

}