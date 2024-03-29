package com.hpcl_paytm.activity.terminal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.DeadObjectException
import android.os.IBinder
import android.os.RemoteException
import android.text.Spanned
import android.util.Log
import android.widget.Toast
import androidx.core.text.HtmlCompat
import com.hpcl_paytm.activity.MyApplication
import com.hpcl_paytm.activity.constants.GlobalMethods
import com.vfi.smartpos.deviceservice.aidl.IBeeper
import com.vfi.smartpos.deviceservice.aidl.IDeviceInfo
import com.vfi.smartpos.deviceservice.aidl.IDeviceService
import com.vfi.smartpos.deviceservice.aidl.IEMV
import com.vfi.smartpos.deviceservice.aidl.IPinpad
import com.vfi.smartpos.deviceservice.aidl.IPrinter
import com.vfi.smartpos.deviceservice.aidl.IScanner
import com.vfi.smartpos.deviceservice.aidl.ISmartCardReader
import com.vfi.smartpos.deviceservice.aidl.PinInputListener
import com.vfi.smartpos.deviceservice.aidl.PinpadKeyType
import com.vfi.smartpos.deviceservice.constdefine.ConstIPinpad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.jvm.Throws

object DeviceService {
    @JvmStatic
    var vfDeviceInfo: IDeviceInfo? = null

    @JvmStatic
    var vfDeviceService: IDeviceService? = null

    @JvmStatic
    var vfPinPad: IPinpad? = null

    @JvmStatic
    var vfBeeper: IBeeper? = null

    @JvmStatic
    var vfIEMV: IEMV? = null

    @JvmStatic
    var vfPrinter: IPrinter? = null

    @JvmStatic
    var vfIScanner: IScanner? = null

    @JvmStatic
    var vfsmartReader: ISmartCardReader? = null

    var pinInputListener: PinInputListener? = null
    val LYRA_IP_ADDRESS = "192.168.250.10"
    val NEW_IP_ADDRESS = "122.176.84.29"
    val NEWAMEXHDFC = "192.168.250.10"
    val NEWAMEXHDFCPort = 4124
    var savedPan = ""
    var PORT = 8101//4124
    lateinit var strnum: String


    fun connectToVFService(context: Context) {
        val conn: ServiceConnection = object : ServiceConnection {
            @SuppressLint("LongLogTag")
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                Log.d("TAG", "onServiceConnected: ..............onservice connected")
                //IDeviceService is used to get Each Interface Object from Interface:-
                vfDeviceService = IDeviceService.Stub.asInterface(service)
                vfDeviceInfo = IDeviceInfo.Stub.asInterface(service)
                vfPinPad = IPinpad.Stub.asInterface(service)
                vfBeeper = IBeeper.Stub.asInterface(service)
                vfPrinter = IPrinter.Stub.asInterface(service)
                vfIScanner = IScanner.Stub.asInterface(service)
                vfsmartReader = ISmartCardReader.Stub.asInterface(service)

                try {
                    vfIEMV = vfDeviceService?.emv
                    vfDeviceInfo = vfDeviceService?.deviceInfo
                    vfBeeper = vfDeviceService?.beeper
                    vfPinPad = vfDeviceService?.getPinpad(1)
                    vfPrinter = vfDeviceService?.printer
                    vfIScanner = vfDeviceService?.getScanner(0)
                    vfsmartReader = vfDeviceService?.getSmartCardReader(0) //0 for Ic terminal

                    val bundle: Bundle? = vfDeviceInfo?.deviceInfo
                    val numericSerialnum = (bundle?.get("VRKSn") ?: "") as? String

                    if (numericSerialnum != null) {
                        Log.d("Device Numeric  No:- ", numericSerialnum)
                    }

                    if (numericSerialnum!=null) {
                        Log.d("Device  Serial No:- ", vfDeviceInfo?.serialNo ?: "")
                        GlobalMethods.putString("serialNumber", vfDeviceInfo?.serialNo ?: "")
                    } else {
                        strnum = String()
                        var number: String? = bundle?.get("VRKSn") as? String


                        var numericSerialnum = number?.split("-")

                        numericSerialnum?.forEach { str ->
                            strnum += str
                        }


                        Log.d("Device Numeric Serial No:- ", strnum)


                    }


                    Log.d("Device IMEI No:- ", vfDeviceInfo?.imei ?: "")
                    GlobalMethods.putString("imeiNumber", vfDeviceInfo?.imei ?: "")
                    Log.d("Android Version:- ", vfDeviceInfo?.androidOSVersion ?: "")
                    GlobalMethods.putString("androidVersion", vfDeviceInfo?.androidOSVersion ?: "")
                    Log.d("Device Model:- ", vfDeviceInfo?.model ?: "")
                    GlobalMethods.putString("deviceModel", vfDeviceInfo?.model ?: "")
                } catch (e: RemoteException) {
                    e.printStackTrace()
                } catch (e: DeadObjectException) {
                    e.printStackTrace()
                }
            }

            override fun onServiceDisconnected(name: ComponentName) {


            }
        }


        val intent = Intent().apply {
            action = "com.vfi.smartpos.device_service"
            `package` = "com.vfi.smartpos.deviceservice"
        }

        val result = context.bindService(intent, conn, Context.BIND_AUTO_CREATE)
        if (!result) {
            Log.i("TAG", "deviceService connect fail!")
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(context, "service binds Failed", Toast.LENGTH_SHORT).show()
            }


        } else {
            Log.i("TAG", "deviceService connect success")
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(context, "service binds successfully", Toast.LENGTH_SHORT).show()
            }

        }
    }

    // Below Methid is used to inject BDK,KSN for DUKPT


    //Below Method is used to Inject Decripted TMK in DeviceService AIDL IPinPad.loadMainKey(int keyId, byte[] key, byte[] checkValue):-
    fun injectTMK(
        decriptedTmk: ByteArray? = null,
        ppk: ByteArray,
        ppkcv: ByteArray?,
        dpk: ByteArray,
        dpkcv: ByteArray?,
        isLoadMainKey: Boolean = true
    ): Boolean? {
        return try {
            var tmkKeyInsertSuccess: Boolean? = false
            if (isLoadMainKey) {
                tmkKeyInsertSuccess = vfPinPad?.loadMainKey(1, decriptedTmk, null)
            }
            Log.d("SUCCESS", "TMK Inserted success")
            vfBeeper?.startBeep(300)
            //showToast("TMK Inserted Successfully!!!")
            if (tmkKeyInsertSuccess == true) {
                injectPPKKey(ppk, null, dpk, null)
            } else if (!tmkKeyInsertSuccess!!) {
                injectPPKKey(ppk, null, dpk, null)
            } else
                false
            return tmkKeyInsertSuccess
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("FAILURE", "TMK Inserted failed")
            false
        }
    }

    //Below Method is used to Inject PPK in DeviceService AIDL IPinPad.loadWorkKey(int keyType, int mkId, int wkId, byte[] key, byte[] checkValue):-
    private fun injectPPKKey(
        ppk: ByteArray,
        ppkcv: ByteArray?,
        dpk: ByteArray,
        dpkcv: ByteArray?
    ): Boolean? {
        return try {
            val ppkKeyInsertSuccess = vfPinPad?.loadWorkKey(PinpadKeyType.PINKEY, 1, 2, ppk, ppkcv)
            Log.d("SUCCESS", "PPK Inserted success")
            println(ppkKeyInsertSuccess)
            vfBeeper?.startBeep(100)
            //       showToast("PPK Inserted Successfully!!!")
            if (ppkKeyInsertSuccess == true)
                injectDPKKey(dpk, dpkcv)
            else
                false
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("FAILURE", "PPK Inserted failed")
            false
        }
    }

    //Below Method is used to Inject DPK in DeviceService AIDL IPinPad.loadWorkKey(int keyType, int mkId, int wkId, byte[] key, byte[] checkValue):-
    private fun injectDPKKey(dpk: ByteArray, dpkcv: ByteArray?): Boolean? {
        return try {
            val dpkKeyInsertSuccess = vfPinPad?.loadWorkKey(PinpadKeyType.TDKEY, 1, 2, dpk, dpkcv)
            vfBeeper?.startBeep(50)
            //       showToast("DPK Inserted Successfully!!!")
            dpkKeyInsertSuccess == true
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("FAILURE", "DPK Inserted failed")
            false
        }
    }

    //Below method is used to Format Text with HTML Tag and pass HTML attributes in it:-
    fun fromHtml(html: String): Spanned {
        return HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    //Below method is used to get IPinPad data:-
    fun getPinPadData(): Boolean? {
        return try {
            vfPinPad?.isKeyExist(12, 1)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            false
        }
    }

    //Below method is used to get DupktKey from IPinPad:-
    fun getDupkt(i: Int, i1: Int, i2: Int, bytes: ByteArray, byteArrayOf: ByteArray): ByteArray? {
        return try {
            val data = vfPinPad?.dukptEncryptData(i, i1, i2, bytes, byteArrayOf)
            data
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }

    //Below method is used to Open Pin Pad in Terminal:-
 /*   fun openPinPad(cardProcessedDataModal: CardProcessedDataModal, activity: Activity) {
        //   mIsoWriter = isoPackageWriter
        //   isOnlinePin = onlinePin
        try {
            doPinPad(cardProcessedDataModal, activity)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/

    /*private fun doPinPad(cardProcessedDataModal: CardProcessedDataModal, activity: Activity) {
        initializePinInputListener(cardProcessedDataModal, activity)
        val param = Bundle()
        val globleparam = Bundle()
        val panBlock: String? = cardProcessedDataModal.getPanNumberData()
        var pinLimit: ByteArray? = null

        if (Enums.CardAid.UnionPay.aid.equals(cardProcessedDataModal.getAID())) {
            pinLimit = byteArrayOf(0, 4, 5, 6, 12) // 0 means bypass pin input
        } else
            pinLimit = byteArrayOf(4, 5, 6, 12)

        param.putByteArray(ConstIPinpad.startPinInput.param.KEY_pinLimit_ByteArray, pinLimit)
        param.putInt(ConstIPinpad.startPinInput.param.KEY_timeout_int, 30)
        when (cardProcessedDataModal.getIsOnline()) {
            1 -> param.putBoolean(ConstIPinpad.startPinInput.param.KEY_isOnline_boolean, true)
            2 -> param.putBoolean(ConstIPinpad.startPinInput.param.KEY_isOnline_boolean, false)
        }
        param.putString(ConstIPinpad.startPinInput.param.KEY_pan_String, panBlock)
        param.putString(ConstIPinpad.startPinInput.param.KEY_promptString_String, "Enter PIN")
        param.putInt(
            ConstIPinpad.startPinInput.param.KEY_desType_int,
            ConstIPinpad.startPinInput.param.Value_desType_3DES
        )
        try {
            vfPinPad?.startPinInput(2, param, globleparam, pinInputListener)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }*/

/*    private fun initializePinInputListener(
        cardProcessedDataModal: CardProcessedDataModal,
        activity: Activity
    ) {
        pinInputListener = object : PinInputListener.Stub() {
            @Throws(RemoteException::class)
            override fun onInput(len: Int, key: Int) {
                Log.d("Data", "PinPad onInput, len:$len, key:$key")
            }

            @Throws(RemoteException::class)
            override fun onConfirm(data: ByteArray?, isNonePin: Boolean) {
                Log.d("Data", "PinPad onConfirm")
                Log.d("MainActivity.TAG", "PinPad byPassPin ---> " + data)

                if (data != null) cardProcessedDataModal.setPinByPass(0)
                else {
                    cardProcessedDataModal.setPinByPass(1)
                    cardProcessedDataModal.setIsOnline(0)
                }

                vfIEMV?.importPin(1, data)
                Log.d(
                    " MainActivity.TAG",
                    "PinPad hex encrypted data ---> " + byte2HexStr(data)
                )
                //savedPinblock = data

                when (cardProcessedDataModal.getReadCardType()) {
                    Enums.DetectCardType.EMV_CARD_TYPE -> {
                        if (cardProcessedDataModal.getIsOnline() == 1) {
                            cardProcessedDataModal.setGeneratePinBlock(byte2HexStr(data))
                            //insert with pin
                            cardProcessedDataModal.setPosEntryMode(Enums.PosEntryModeType.EMV_POS_ENTRY_PIN.posEntry.toString())
                        } else {
                            cardProcessedDataModal.setGeneratePinBlock("")
                            //off line pin
                            cardProcessedDataModal.setPosEntryMode(Enums.PosEntryModeType.EMV_POS_ENTRY_OFFLINE_PIN.posEntry.toString())
                        }
                    }

                    Enums.DetectCardType.CONTACT_LESS_CARD_TYPE -> {
                        if (cardProcessedDataModal.getIsOnline() == 1) {
                            cardProcessedDataModal.setGeneratePinBlock(byte2HexStr(data))
                            cardProcessedDataModal.setPosEntryMode(Enums.PosEntryModeType.CTLS_EMV_POS_WITH_PIN.posEntry.toString())
                        } else {
                            cardProcessedDataModal.setGeneratePinBlock("")
                            // cardProcessedDataModal.setPosEntryMode(Enums.PosEntryModeType.EMV_POS_ENTRY_PIN.posEntry.toString())
                        }
                    }

                    Enums.DetectCardType.MAG_CARD_TYPE -> {
                        //   vfIEMV?.importPin(1, data) // in Magnetic pin will not import
                        cardProcessedDataModal.setGeneratePinBlock(byte2HexStr(data))

                        if (cardProcessedDataModal.getFallbackType() == EFallbackCode.EMV_fallback.fallBackCode)
                            cardProcessedDataModal.setPosEntryMode(Enums.PosEntryModeType.EMV_POS_ENTRY_FALL_MAGPIN.posEntry.toString())
                        else
                            cardProcessedDataModal.setPosEntryMode(Enums.PosEntryModeType.POS_ENTRY_SWIPED_NO4DBC_PIN.posEntry.toString())
                        cardProcessedDataModal.setApplicationPanSequenceValue("00")
                    }

                    else -> {
                    }
                }
            }

            @Throws(RemoteException::class)
            override fun onCancel() {
                Log.d("Data", "PinPad onCancel")
                try {
                    if (null != activity) {
                        GlobalScope.launch(Dispatchers.Main) {
                            (activity as CardDetectActivity).declinedTransaction()
                        }
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

            @Throws(RemoteException::class)
            override fun onError(errorCode: Int) {
                Log.d("Data", "PinPad onError 3, code:$errorCode")
                try {
                    if (null != activity) {
                        GlobalScope.launch(Dispatchers.Main) {
                            //(activity as CardDetectActivity).declinedTransaction()
                            (activity as CardDetectActivity).openTimeout()
                        }
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }*/


    //Below method is used to set AID & RID :-

    //Below method is used to show Toast on UI Thread:-

    fun showToast(message: String) {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(MyApplication.appContext, message, Toast.LENGTH_SHORT).show()
        }
    }


}

enum class EFallbackCode(var fallBackCode: Int) {
    Swipe_fallback(111),
    EMV_fallback(8),
    NO_fallback(0),
    EMV_fallbackNew(12),
    CTLS_fallback(333)
}

enum class CardErrorCode(var errorCode: Int) {
    EMV_FALLBACK_ERROR_CODE(3),
    CTLS_ERROR_CODE(6)
}
