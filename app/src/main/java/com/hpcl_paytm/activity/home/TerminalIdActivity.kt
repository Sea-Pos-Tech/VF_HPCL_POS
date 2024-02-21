package com.hpcl_paytm.activity.home

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PowerManager
import android.util.Base64
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.hpcl_paytm.R
import com.hpcl_paytm.activity.MyApplication
import com.hpcl_paytm.activity.SettlementDialog
import com.hpcl_paytm.activity.apicall.Utils
import com.hpcl_paytm.activity.constants.AppUtils
import com.hpcl_paytm.activity.constants.Constants
import com.hpcl_paytm.activity.constants.GlobalMethods
import com.hpcl_paytm.activity.constants.NetworkUtil
import com.hpcl_paytm.activity.constants.SharedPreferencesData
import com.hpcl_paytm.activity.constants.ToastMessages
import com.hpcl_paytm.activity.constants.TransactionUtils
import com.hpcl_paytm.activity.constants.Validation
import com.hpcl_paytm.activity.encryption_decryption.AesDesWrapper
import com.hpcl_paytm.activity.room.repository.AppRepository
import com.hpcl_paytm.activity.utils.RSAUtils
import com.hpcl_paytm.databinding.ActivityMainBinding
import com.hpcl_paytm.databinding.ActivityOperatoridBinding

class TerminalIdActivity : AppCompatActivity() {
    private var settlementDialog: SettlementDialog? = null
    lateinit var sharedPreferencesData: SharedPreferencesData
    private var terminalId_edittext: EditText?=null
    private var terminal_pin_editText: EditText?=null
    private var confirm_terminal_pin_Edittext: EditText?=null
    private var wl: PowerManager.WakeLock?=null
    private var flag:Int=0;
    private lateinit var binding: ActivityOperatoridBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        binding = ActivityOperatoridBinding.inflate(layoutInflater)


      //  disableBottomNavigation()
        getingIVKey()
        sharedPreferencesData.setSharedPreferenceData(
            Constants.ENCRYPT_MODE,
            Constants.ENCRYPT_MODE_FLAG,
            "false"
        )

        window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        terminalId_edittext = findViewById<TextInputEditText>(R.id.terminalId_edittext)
        terminal_pin_editText = findViewById<TextInputEditText>(R.id.serverIpEdittext)
        confirm_terminal_pin_Edittext = findViewById<TextInputEditText>(R.id.secondServerIpEdittext)
        var confirmButton = findViewById<Button>(R.id.confirm)
        sharedPreferencesData = SharedPreferencesData(this)
        RSAUtils.disableCopyPasteOperations(binding.terminalIdEdittext)
        RSAUtils.disableCopyPasteOperations(binding.serverIpEdittext)
        RSAUtils.disableCopyPasteOperations(binding.secondServerIpEdittext)
        binding.confirm.setOnClickListener {
            if (!Validation.isValidTerminalId(terminalId_edittext?.text.toString())) {
                terminalId_edittext?.error = "Please Enter Valid Terminal Id"

                return@setOnClickListener
            }
            if (terminal_pin_editText?.text.toString().equals("", ignoreCase = true) &&
                confirm_terminal_pin_Edittext?.text.toString().equals("", ignoreCase = true)
            ) {
                terminal_pin_editText?.error = getString(R.string.input_required)
                confirm_terminal_pin_Edittext?.setError(getString(R.string.input_required))
            } else if (terminal_pin_editText?.text.toString().equals("", ignoreCase = true)) {
                terminal_pin_editText?.error = getString(R.string.input_required)
            } else if (confirm_terminal_pin_Edittext?.text.toString()
                    .equals("", ignoreCase = true)
            ) {
                confirm_terminal_pin_Edittext?.error = getString(R.string.input_required)
            } else if (!terminal_pin_editText?.text.toString()
                    .equals(confirm_terminal_pin_Edittext?.text.toString(), ignoreCase = true)
            ) {
                confirm_terminal_pin_Edittext?.error = resources.getString(R.string.mismatchterminalpin)
                //  ToastMessages.customMsgToast(this, resources.getString(R.string.mismatchterminalpin))
            } else if (terminal_pin_editText?.text.toString().length < 4 && confirm_terminal_pin_Edittext?.text.toString().length < 4) {
                ToastMessages.customMsgToast(this, resources.getString(R.string.min_and_max_length))
            } else if (terminal_pin_editText?.text.toString().equals("0000", ignoreCase = true) &&
                confirm_terminal_pin_Edittext?.text.toString().equals("0000", ignoreCase = true)
            ) {
                ToastMessages.customMsgToast(this, resources.getString(R.string.four_zeros))
            } else {
                TransactionUtils.setTerminalPin(
                    this,
                    terminal_pin_editText?.text.toString(),
                    Constants.TERMINAL_PIN
                )
                showDialog()
                callMainActivity(terminalId_edittext?.text.toString())
            }
        }
        terminalId_edittext?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (!Validation.isValidTerminalId(terminalId_edittext?.text.toString())) {
                    terminalId_edittext?.error = "Please Enter Valid Terminal Id"
                    false
                }
                if (terminal_pin_editText?.text.toString().equals("", ignoreCase = true) &&
                    confirm_terminal_pin_Edittext?.text.toString().equals("", ignoreCase = true)
                ) {
                    terminal_pin_editText?.error = getString(R.string.input_required)
                    confirm_terminal_pin_Edittext?.error = getString(R.string.input_required)
                } else if (terminal_pin_editText?.text.toString().equals("", ignoreCase = true)) {
                    terminal_pin_editText?.error = getString(R.string.input_required)
                } else if (confirm_terminal_pin_Edittext?.text.toString()
                        .equals("", ignoreCase = true)
                ) {
                    confirm_terminal_pin_Edittext?.error = getString(R.string.input_required)
                } else if (!terminal_pin_editText?.text.toString()
                        .equals(confirm_terminal_pin_Edittext?.text.toString(), ignoreCase = true)
                ) {
                    confirm_terminal_pin_Edittext?.error = resources.getString(R.string.mismatchterminalpin)
                    // ToastMessages.customMsgToast(this, resources.getString(R.string.mismatchterminalpin))
                } else {
                    //  TransactionUtils.setTerminalPin(this, terminal_pin_editText.text.toString(), Constants.TERMINAL_PIN)
                    // callMainActivity(terminalId_edittext?.text.toString())
                }
                true
            }
            false
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun callMainActivity(terminalId: String) {
        if (!TransactionUtils.isTerminalRegistered(this, Constants.REGISTRATION_STATUS)
            && TransactionUtils.getTerminalPin(this, Constants.TERMINAL_PIN) == "") {
            ToastMessages.customMsgToast(this,"Please Enter Terminal Pin")
            settlementDialog?.dismiss()
            return
        }
        else if (!TransactionUtils.isTerminalRegistered(this, Constants.REGISTRATION_STATUS)) {
            //val serverIp="https://dtpapi.mloyalretail.com"
            //val serverIp="http://dtpapi.mloyalcapture.com"
            //val serverIp="https://posapi.mloyalcapture.com"
            //val serverIp="https://dtpapitestv1.mloyalcapture.com"
            var serverIp:String?=null
            var url= AppRepository().getMerchantDetails()?.URL
            if(url!=null && !url.equals("")) {
                serverIp=url
            }else{
                serverIp= Utils.MAIN_URL
            }
            GlobalMethods.setTerminalId(terminalId.trim(), this)
            GlobalMethods.setServerIp(serverIp.trim(), this)
            GlobalMethods.setSecondServerIp(serverIp.trim(), this)
            isNetworkAvailable()
        } else {
            ToastMessages.customMsgToast(this, "Terminal Already Registered")
        }
    }

    fun isNetworkAvailable() {
        callRegistrationApi()

    }

    private fun callRegistrationApi() {
        val performRegistartion = PerformRegistartion(this).constructRegistrationRequest()
        val viewModel = ViewModelProvider(this).get(SettingDashboardViewModel::class.java)
        if (performRegistartion != null) {
            viewModel.makeApiRegistration(performRegistartion)
        }
        Log.e("Registration req","Registration Req:"+ Gson().toJson(performRegistartion))
        viewModel.getliveRegistration()?.observe(this) { apiResponse ->
            try {
                if(apiResponse!=null) {
                    when (apiResponse) {
                       Result.Error -> {
                            if(apiResponse.error.contains("Please Try Again",true)) {
                                settlementDialog?.dismiss()
                                getToken("")
                            }
                            else {
                                callSettlementOnFailureDialog(resources.getString(R.string.internal_server_error))
                            }
                        }
                        is com.paytm.hpclpos.livedatamodels.registrationapi.ApiResponse.RegistrationResponse -> {
                            if (apiResponse.Success) {
                                Log.e("apiResponse.Data","apiResponse.Data:"+ Gson().toJson(apiResponse))
                                terminalId_edittext?.setText("")
                                terminal_pin_editText?.setText("")
                                confirm_terminal_pin_Edittext?.setText("")
                                if (apiResponse.Internel_Status_Code == Constants.STATUS_SUCCESS) {
                                    val data = apiResponse.Data
                                    Log.e("apiResponse.Data","apiResponse.Data:"+ Gson().toJson(apiResponse.Data))
                                    Log.e("apiResponse.Data","Merchant ID:"+ Gson().toJson(data.ObjGetMerchantDetail!![0].MerchantId))
                                    Log.e("apiResponse.Data","TerminalId:"+ Gson().toJson(data.ObjGetMerchantDetail!![0].TerminalId))
                                    if(data.ObjGetMerchantDetail!![0].MerchantId!=null && data.ObjGetMerchantDetail!![0].TerminalId!=null) {
                                        settlementDialog!!.onSuccess()
                                        Handler(Looper.getMainLooper()).postDelayed(
                                            { settlementDialog!!.dismiss() }, 1000)
                                        TransactionUtils.setBatchNumber(this,Constants.BATCH,data.ObjGetMerchantDetail!![0].BatchNo!!?:"1")
                                        viewModel.storeRegistrationDataIntoDb(data, this)
                                        if(!apiResponse.Data.ObjGetMerchantDetail?.get(0)?.Reason.equals("Last Batch is not Settled")) {
                                            var serverTime:String?=AppRepository(this).getMerchantDetails()?.ServerTime
                                            var ret=AppUtils.setServerTime(serverTime)
                                            GlobalMethods.setIsTerminalPinFlag(this,false)
                                            Log.e("Set Date Ret","Set Date return:"+ret)
                                            if(flag==0) {
                                                printRegisterReceipt()
                                            }
                                        }
                                        GlobalMethods.setAutoSettleDateTime(data.ObjGetMerchantDetail!![0]?.ServerTime,this)
                                    }else{
                                        callSettlementOnFailureDialog(data.message)
                                        ToastMessages.customMsgToast(this, "Registration Failed")
                                    }

                                }
                            } else {
                                if (apiResponse.Success==false) {
                                    viewModel.getliveRegistration()?.removeObserver { this }
                                    if(apiResponse.Message.equals("Token Expired")) {
                                        settlementDialog!!.dismiss()
                                        getToken("Register")
                                    }else{
                                        settlementDialog!!.dismiss()
                                        Toast.makeText(this,""+apiResponse.Data.ObjGetMerchantDetail?.get(0)?.Reason,
                                            Toast.LENGTH_LONG).show()
                                        showPopupFailedReg(apiResponse.Data.ObjGetMerchantDetail?.get(0)?.Reason)
                                        //  callSettlementOnFailureDialog(apiResponse.Data.ObjGetMerchantDetail?.get(0)?.Reason)
                                    }
                                }else{
                                    callSettlementOnFailureDialog(this.resources.getString(R.string.internal_server_error))
                                }
                            }
                        }
                    }
                }  else{
                    callSettlementOnFailureDialog(this.resources.getString(R.string.internal_server_error))
                    ToastMessages.customMsgToast(this, this.resources.getString(R.string.internal_server_error))
                }
            }
            catch (ex :Exception) {
                ex.printStackTrace()
                callSettlementOnFailureDialog("Exception")
            }

        }
    }

    private fun showDialog() {
        settlementDialog = SettlementDialog(this)
        settlementDialog!!.setTitle(getString(R.string.title_registration))
        settlementDialog!!.setVisibilityForSettlementStatus()
        settlementDialog!!.setProcessing()
        settlementDialog!!.settimerAndEndpoint(GlobalMethods.getServerIp(this) + ".....")
        settlementDialog!!.setpacketStatus(this.resources.getString(R.string.receiving))
        settlementDialog!!.show()
    }



    fun getingIVKey(){
        try {
            sharedPreferencesData = SharedPreferencesData(MyApplication.appContext)
            sharedPreferencesData.setSharedPreferenceData(Constants.ENCRYPTKEYPREF, Constants.KEY, "")
            sharedPreferencesData.setSharedPreferenceData(Constants.ENCRYPTIVPREF, Constants.IV, "")

            val IV= AppUtils.IV!!
            val secretKey = AesDesWrapper.generateKey()
            Log.e("Plain Secret Key","Plain Secret Key: "+ Base64.encodeToString(secretKey?.getEncoded(), Base64.DEFAULT))
            var strIV= RSAUtils.byteArrayToHexString(IV)
            var encryptedIV = AesDesWrapper.encryptByPublicKey(strIV,this)
            sharedPreferencesData.setSharedPreferenceData(Constants.ENCRYPTIVPREF, Constants.IV, encryptedIV)
            sharedPreferencesData.setSharedPreferenceData(Constants.PLAN_IVPREF, Constants.PLAN_IV, strIV)
            var strKeyEncrypted= AesDesWrapper.encryptByPublicKey(secretKey?.getEncoded()
                ?.let { RSAUtils.byteArrayToHexString(it) },this)
            sharedPreferencesData.setSharedPreferenceData(Constants.PLAN_KEYPREF, Constants.PLAN_KEY,
                secretKey?.getEncoded()?.let { RSAUtils.byteArrayToHexString(it) })
            sharedPreferencesData.setSharedPreferenceData(Constants.ENCRYPTKEYPREF, Constants.KEY, strKeyEncrypted)

            Log.e("Encrypted Key","Encrypted Key: "+strKeyEncrypted)
            Log.e("Encrypted IV","Encrypted IV: "+encryptedIV)


            /// encryptedVal = AesDesWrapper.encryptAndEncode(reversalObject, IV, 256,secretKey)
            ////   Log.e("Plain Request Body","Plain Request Body: "+reversalObject)
            ///  Log.e("Encrypted Request Body","Encrypted Request Body: "+encryptedVal)
        }catch (ex:java.lang.Exception) {
            ex.printStackTrace()
        }
    }
}