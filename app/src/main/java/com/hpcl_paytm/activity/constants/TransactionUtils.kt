package com.hpcl_paytm.activity.constants

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hpcl_paytm.activity.MyApplication
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class TransactionUtils {
    companion object {
        private val PREFS = "cposprefs"
        private val BATCH_FORMAT = "%03d"

        fun incrementBatch(context: Context, batchName: String?): String {
            val pref = context.getSharedPreferences(PREFS, 0) // 0 for private
            var batch = pref.getInt(batchName, 1)
            batch++
          /*  if (batch == AppRepository(context).getMerchantDetails()?.BatchSize?.toInt()) {
                batch = 1
            }*/
            val editor = pref.edit()
            editor.putInt(batchName, batch)
            editor.apply()
            return  batch.toString()
        }

       /* fun getCurrentBatch(context: Context, batchName: String?): String {
            val pref = context.getSharedPreferences(PREFS, 0) // 0 for private
            val currentBatchNumber = pref.getInt(batchName, AppRepository(context).getMerchantDetails().BatchNo!!.toInt())
            return String.format(BATCH_FORMAT, currentBatchNumber)
        }*/

        fun getCurrentBatch(context: Context?, batchName: String?): String {
            val pref = context?.getSharedPreferences(PREFS, 0) // 0 for private
            val currentBatchNumber = pref?.getInt(batchName, 0)
            return currentBatchNumber.toString()
        }

        fun setBatchNumber(context: Context, batchName: String?, batchNumber: String) {
            val pref = context.getSharedPreferences(PREFS, 0) // 0 for private
            val editor = pref.edit()
            editor.putInt(batchName, batchNumber.toInt())
            editor.apply()
        }

        fun isTerminalRegistered(context: Context, prefName: String): Boolean {
            val pref = context.getSharedPreferences(PREFS, 0) // 0 for private
            val register = pref.getBoolean(prefName, false)
            return register
        }

        fun setTerminalRegistrationStatus(context: Context, prefName: String) {
            val pref = context.getSharedPreferences(PREFS, 0) // 0 for private
            val editor = pref.edit()
            editor.putBoolean(prefName, true)
            editor.apply()
        }

        fun setTerminalPin(context: Context, terminalPin: String, prefName: String) {
            val pref = context.getSharedPreferences(PREFS, 0) // 0 for private
            val editor = pref.edit()
            editor.putString(prefName, terminalPin)
            editor.apply()
        }

        fun getTerminalPin(context: Context, prefName: String): String {
            val pref = context.getSharedPreferences(PREFS, 0) // 0 for private
            return pref.getString(prefName, "")!!
        }

        fun handleExceptions(t: Throwable): String {
            when (t) {
                is UnknownHostException -> {
                    return if (NetworkUtil.checkNetworkStatus(MyApplication.appContext!!))
                    { "Please enter a valid Server Ip" } else { "Please check Your Internet Connection" }
                }
                is SocketTimeoutException-> {
                    return "Socket Time Out"
                }
                else -> {
                    if(t.localizedMessage.equals("timeout")) {
                        return "timeout"
                    }else{
                        return "Please Try Again"
                    }
                }
            }
            return "timeout"
        }

          }
}