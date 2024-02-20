
package com.hpcl_paytm.activity.constants

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import java.util.*
import kotlin.collections.HashMap


class GlobalMethods {

    companion object {
        private var datas: HashMap<String, Any?>? = null

        fun initAndClear() {
            if (datas == null) {
                datas = HashMap()
            } else {
                datas!!.clear()
            }
        }

        var sp: SharedPreferences? = null
        var editor: SharedPreferences.Editor? = null
        var context: Context? = null
        private val VAL = "000001"
        //For Reversal
        private var editorReversal: SharedPreferences.Editor? = null
        var sharedpreferences: SharedPreferences? = null
        private const val SHARED_PREFERENCES_REVERSAL = "reversal"
        private const val SHARED_PREFERENCES_KEY_REVERSAL = "reversal_data"

        private const val SHARED_PREFERENCES_REVERSAL_HP_PAYBACK = "reversal_hp_payback"
        private const val SHARED_PREFERENCES_KEY_REVERSAL_HP_PAYBACK = "reversal_data_hp_payback"
        private val PROCESS_CCN = "PROCESS_CCN"
        fun getTransactionId(context: Context): String? {
            Log.d("Global Methods", "Get Transaction Id Called")
            initSharedPreference(context)
            val s = Constants.getTrace(context)
            val s1 = strNumAuto(s!!)
            setTrace(context, s1)
            Log.d("Global Methods","Invoice Id "+s)
            return numToStr6(s)
        }

        // For development purpose
        fun displayTransId(context: Context) : String{
            initSharedPreference(context)
            return Constants.getTrace(context)!!
        }

        fun decrementTransactionIdByOne(context: Context, invoiceNum: String) {
            Log.d("Global Methods", "Decrements Transaction Id By one")
            initSharedPreference(context)
            val s1 = strNumAutoDecrement(invoiceNum)
            setTrace(context, s1)
        }

        fun strNumAuto(s: String): String? {
            var i = s.toInt()
            i++
            val s1 = i.toString()
            //return numToStr6(s1)
            return s1
        }

        fun strNumAutoDecrement(s: String): String? {
            return numToStr6(s)
        }

        fun numToStr6(numString: String): String? {
            var numString = numString
            if (TextUtils.isEmpty(numString)) {
                numString = VAL
            }
            if (numString.length > 6) {
                numString = VAL
            } else if (numString.length < 6) {
                val str = StringBuilder()
                for (i in 0 until 6 - numString.length) {
                    str.append("0")
                }
                numString = str.append(numString).toString().trim { it <= ' ' }
            }
            return numString
        }

        fun setTrace(context: Context?, msg: String?) {
            val s: String? = numToStr6(msg!!)
            Constants.setTrace(context, s)
        }

        fun initSharedPreference(context: Context) {
            this.context = context
            this.sp =
                (context.applicationContext
                    .getSharedPreferences("hpclData", Context.MODE_PRIVATE))
            this.editor = sp!!.edit()
        }

        fun putString(key: String?, value: String?) {
            editor!!.putString(key, value).apply()
        }

        fun getString(key: String?): String? {
            return getString(key, null)
        }

        fun getString(key: String?, defaultValue: String?): String? {
            return sp!!.getString(key, defaultValue)
        }

        fun setIsLoginOperator(context: Context?, status: Boolean) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val statusLocked = prefs.edit().putBoolean(Constants.LOGIN, status).apply()
        }

        fun getIsLoginOperator(context: Context): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getBoolean(Constants.LOGIN, false)
        }

        fun setIsTerminalPinFlag(context: Context?, status: Boolean) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val statusLocked = prefs.edit().putBoolean(Constants.TERMINAL_PIN_BLOCK, status).apply()
        }

        fun getIsTerminalPinFlag(context: Context): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getBoolean(Constants.TERMINAL_PIN_BLOCK, false)
        }

        fun setCheckLoginOperator(context: Context?, status: Boolean) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val statusLocked = prefs.edit().putBoolean(Constants.OPERATOR, status).apply()
        }

        fun getCheckLoginOperator(context: Context): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getBoolean(Constants.OPERATOR, false)
        }

        fun setIsKeyExchange(context: Context, status: Boolean) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val statusLocked = prefs.edit().putBoolean(Constants.KEY_EXCHANGE, status).apply()
        }

        fun getIsKeyExchange(context: Context): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getBoolean(Constants.KEY_EXCHANGE, false);
        }

        fun setIsLogon(context: Context, status: Boolean) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val statusLocked = prefs.edit().putBoolean(Constants.KEY_LOGON, status).apply()
        }

        fun getIsLogon(context: Context): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getBoolean(Constants.KEY_LOGON, false);
        }

        private val CHEQUE_NUMBER = "CHEQUE_NUMBER"
        private val MICR_CODE = "MICR_CODE"
        private val CONTROL_CARD_NUMBER = "CONTROL_CARD_NUMBER"
        private val TRANSACTION_TYPE = "TRANSACTION_TYPE"
        private val MOBILE_NUMBER_FOR_TRANSACTION = "MOBILE_NUMBER_FOR_TRANSACTION"
        private val VEHICLE_NUMBER = "VEHICLE NUMBER"
        private val PRODUCT_TYPE = "PRODUCT_TYPE"
        private val PRODUCT_ID = "PRODUCT_ID"
        private val BANK_ID = "BANK_ID"
        private val TRANS_ID = "TRANS_ID"
        private val SALE_TYPE = "SALE_TYPE"
        private val AMOUNT = "AMOUNT"
        private val ROC = "ROC"
        private val POINTS = "POINTS"
        private val AMOUNT_VIEW_MODEL = "AMOUNT_VIEW_MODEL"
        private val CARD_INFO_ENTITY = "CARD_INFO_ENTITY"
        private val PIN_DATA = "PIN_DATA"
        private val UTR_NUMBER = "UTR_NUMBER"
        private val TOKEN_NUMBER = "TOKEN_NUMBER"
        private val OTP_NUMBER = "OTP_NUMBER"
        private val REPORT_TYPE = "REPORT TYPE"
        private val CARD_NUM = "CARD_NUM"
        private val CARD_TYPE = "CARD_TYPE"
        private val MICR_NUMBER = "MICR_NUMBER"
        private val TAG_ID = "TAG_ID"
        private val STAN_NUM = "STAN_NUM"
        private val FORM_FACTOR = "FORM_FACTOR"
        private val FAST_TAG_TRANS_NO = "FAST_TAG_TRANS_NO"
        private val FAST_TAG_TRANS_ID = "FAST_TAG_TRANS_ID"
        private val FAST_TAG_TRANS_TIME = "FAST_TAG_TRANS_TIME"
        private val INVOICE_ID = "INVOICE_ID"
        private val TXN_DATE = "TXN_DATE"
        private val PIN_KEY = "PIN_KEY"
        private val PIN_KEY_REF = "PIN_KEY_REF"
        private val TRANS_FLAG_KEY = "TRANS_FLAG_KEY"
        private val TRANS_FLAG_REF = "TRANS_FLAG_REF"
        private val DATA_KEY = "DATA_KEY"
        private val DATA_KEY_REF = "DATA_KEY_REF"
        private val LAST_BATCH_KEY = "LAST_BATCH_KEY"
        private val LAST_BATCH_REF = "LAST_BATCH_REF"
        private val BANK_NAME = "BANK_NAME"
        private val TODAY_DATE = "TODAY_DATE"
        private val BATCH_SETTLE = "BATCH_SETTLE"
        private val BATCH_SETTLE_FLAG = "BATCH_SETTLE_FLAG"
        private val BATCH_SETTLE_REF = "BATCH_SETTLE_REF"
        private val BATCH_SETTLE_REF_FLAG = "BATCH_SETTLE_REF_FLAG"
        private val BATCH_NAME_REF = "BATCH_NAME_REF"
        private val BANK_NAME_REF = "BANK_NAME_REF"
        private val TODAY_DATE_REF = "TODAY_DATE_REF"
        private val PAY_CODE = "PAY_CODE"
        private val PAY_CODE_KEY = "PAY_CODE_KEY"
        private val BALANCE = "BALANCE"
        private val VOID_TXN_ID = "VOID_TXN_ID"
        private val ODOMETER = "ODOMETER"
        private val VOID_TRANSACTION_TYPE = "VOID_TRANSACTION_TYPE"
        private val IS_RUNNING = "IS_RUNNING"
        private val IS_RUNNING_KEY = "IS_RUNNING_KEY"
        private val TXN_TYPE_REVERSAL = "TXN_TYPE_REVERSAL"
        private val TYPE_REVERSAL_KEY = "TYPE_REVERSAL_KEY"
        private val VOID_SALE_TYPE = "VOID_SALE_TYPE"
        private val VOID_SALE_TYPE_KEY = "VOID_SALE_TYPE_KEY"
        private val SETTLE_TYPE = "SETTLE_TYPE"
        private val SETTLE_TYPE_KEY = "SETTLE_TYPE_KEY"
        private val REF_KEY = "REF_KEY"
        private val REF_KEY_REF = "REF_KEY_REF"
        var chequeNumber = ""
        var micrCode = ""
        private var controlCardNumber = ""
        var TransactionID = "101202"
        var transactionType: String = ""
        var mobileNoForTrans: String = ""
        var productTypeP: String = ""
        var saleTypeP: String = ""
        var amountP: String = ""
    //    var cardInfoEntity: CardInfoEntity? = null
        var pinData: String? = null
        var utrNumber: String? = null
        var cardNom: String = ""
        var status = true
        var micrNo: String = ""
        var chequeNo: String = ""

        fun getDeviceId(context: Context?): String {
            var androidId = Settings.Secure.getString(context?.getContentResolver(), Settings.Secure.ANDROID_ID);
            Log.e("GLOBALMETHODS", "DEVICEID=" + androidId)
            return androidId
        }
        //var appRepository:AppRepository?=null
        var TestMerchant_Id: Long = 0
        var TestOutLetId = 3089798987
        //var TestTID: Long = 5064726187
        var TestCARDNO = 7000210078041570
        var TestTerminalPin = 1234
        var TestRoleId = 2
        var TestUserType = 1

        fun setTerminalId(terminalId: String,context: Context) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(Constants.TOKENIDPREF,Constants.TERMINAL_ID , terminalId)
        }

        fun getTerminalId(context: Context) : Long? {
            val sharedPref = SharedPreferencesData(context)
            var terminalId:Long?=null
            if(sharedPref.getSharedPreferenceData(Constants.TOKENIDPREF,Constants.TERMINAL_ID)!=null && !sharedPref.getSharedPreferenceData(Constants.TOKENIDPREF,Constants.TERMINAL_ID).equals("null") && !
                sharedPref.getSharedPreferenceData(Constants.TOKENIDPREF,Constants.TERMINAL_ID).equals("")) {
                terminalId=sharedPref.getSharedPreferenceData(Constants.TOKENIDPREF,Constants.TERMINAL_ID)?.toLong()
            }else{
               // terminalId=AppRepository(context).getMerchantDetails()?.TerminalId
            }
            return terminalId
        }

        fun setServerIp(serverIp: String,context: Context) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(Constants.TOKENIDPREF,Constants.SERVER_IP , serverIp)

        }

        fun getServerIp(context: Context) : String {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(Constants.TOKENIDPREF,Constants.SERVER_IP)!!
        }

        fun setTokenDate(tokenDate: String,context: Context) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(Constants.TOKENDATEPREF,Constants.TOKEN_DATE , tokenDate)

        }

        fun getTokenDate(context: Context) : String {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(Constants.TOKENDATEPREF,Constants.TOKEN_DATE)!!
        }




        fun setTMK(tmk: String,context: Context) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(Constants.TMKPREF,Constants.TMK_KEY , tmk)
        }

        fun getTMK(context: Context) : String {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(Constants.TMKPREF,Constants.TMK_KEY)!!
        }

        fun setSecondServerIp(serverIp: String,context: Context) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(Constants.TOKENIDPREF,Constants.SECOND_SERVER_IP , serverIp)
        }

        fun getSeocndServerIp(context: Context) : String {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(Constants.TOKENIDPREF,Constants.SECOND_SERVER_IP)!!
        }

        fun setMobileNo(mobileNo: String?) {
            datas!!.put(MOBILE_NUMBER_FOR_TRANSACTION, mobileNo)
        }

        fun getMobileNo(): String? {
            return datas!!.get(MOBILE_NUMBER_FOR_TRANSACTION) as? String
        }

        fun setTxnDate(Date: String) {
            datas!!.put(TXN_DATE, Date)
        }

        fun getTXNDate(): String? {
            return datas!!.get(TXN_DATE) as? String
        }


        fun setVoidBalance(Date: String) {
            datas!!.put(BALANCE, Date)
        }

        fun getVoidBalance(): String? {
            return datas!!.get(BALANCE) as? String
        }

        fun setBankName(bankName: String?,context: Context?) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(BANK_NAME, BANK_NAME_REF,bankName)
        }

        fun getBankName(context:Context): String? {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(BANK_NAME,BANK_NAME_REF)
        }

        fun setTodayDate(date: String?,context: Context?) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(TODAY_DATE, TODAY_DATE_REF,date)
        }

        fun getTodayDate(context:Context): String? {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(TODAY_DATE,TODAY_DATE_REF)
        }

        fun setIsBatchSettle(batchSettle: String,context: Context?) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(BATCH_SETTLE, BATCH_SETTLE_REF,batchSettle)

        }

        fun getIsBatchSettle(context: Context?): String? {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(BATCH_SETTLE,BATCH_SETTLE_REF)
        }

        fun setPinKey(pinkey: String,context: Context) {
            //   datas!!.put(PIN_KEY, pinkey)
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(PIN_KEY, PIN_KEY_REF,pinkey)
        }

        fun getPinKey(context: Context): String? {
            // return datas!!.get(PIN_KEY) as? String
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(PIN_KEY,PIN_KEY_REF)
        }

        /*  fun setTransFlagKey(transKey: String,context: Context) {
              val sharedPref = SharedPreferencesData(context)
              sharedPref.setSharedPreferenceData(TRANS_FLAG_KEY,TRANS_FLAG_REF,transKey)
          }

          fun getTransFlagKey(context: Context): String? {
              val sharedPref = SharedPreferencesData(context)
              return sharedPref.getSharedPreferenceData(TRANS_FLAG_KEY,TRANS_FLAG_REF)
          }
  */
        fun setDataKey(datakey: String,context: Context) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(DATA_KEY,DATA_KEY_REF,datakey)
        }

        fun getDataKey(context: Context): String? {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(DATA_KEY,DATA_KEY_REF)
        }

        fun setLastBatchId(batchId: String,context: Context?) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(LAST_BATCH_KEY,LAST_BATCH_REF,batchId)
        }

        fun getLastBatchId(context: Context?): String? {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(LAST_BATCH_KEY,LAST_BATCH_REF)
        }

        fun setTAG_ID(TagId: String) {
            datas!!.put(TAG_ID, TagId)
        }

        fun getTAG_ID(): String? {
            return datas!!.get(TAG_ID) as? String
        }

        fun setStan(Stan: String?) {
            datas!!.put(STAN_NUM, Stan)
        }

        fun getStan(): String? {
            return datas?.get(STAN_NUM) as? String
        }

        fun setOdoMeter(Stan: String?) {
            datas!!.put(ODOMETER, Stan)
        }

        fun getOdoMeter(): String? {
            return datas?.get(ODOMETER) as? String
        }

        fun setTrans_No(TransNo: String) {
            datas!!.put(FAST_TAG_TRANS_NO, TransNo)
        }

        fun getTrans_No(): String? {
            return datas!!.get(FAST_TAG_TRANS_NO) as? String
        }

        fun setInvoice_No(Invoice: String?) {
            datas!!.put(INVOICE_ID, Invoice)
        }

        fun getInvoice_No(): String? {
            return datas!!.get(INVOICE_ID) as? String
        }

        fun setVoidRefNo(Invoice: String?) {
            datas!!.put(VOID_TXN_ID, Invoice)
        }

        fun getVoidRefNo(): String? {
            return datas!!.get(VOID_TXN_ID) as? String
        }


        fun setFastTagTrans_ID(FastTagTransID: String) {
            datas!!.put(FAST_TAG_TRANS_ID, FastTagTransID)
        }

        fun getFastTagTrans_ID(): String? {
            return datas!!.get(FAST_TAG_TRANS_ID) as? String
        }

        fun setFastTagTrans_Time(FastTagTransTime: String) {
            datas!!.put(FAST_TAG_TRANS_TIME, FastTagTransTime)
        }

        fun getFastTagTrans_Time(): String? {
            return datas!!.get(FAST_TAG_TRANS_TIME) as? String
        }

        fun setVehilceNo(mobileNo: String?) {
            datas!!.put(VEHICLE_NUMBER, mobileNo)
        }

        fun getVehilceNo(): String? {
            return datas!!.get(VEHICLE_NUMBER) as? String
        }

        fun setProductType(productType: String?) {
            datas!!.put(PRODUCT_TYPE, productType)
        }

        fun getProductType(): String? {
            return datas!!.get(PRODUCT_TYPE) as? String
        }

        fun setProductID(productId: Int) {
            datas!!.put(PRODUCT_ID, productId)
        }

        fun getProductID(): Int? {
            return datas!!.get(PRODUCT_ID) as? Int
        }

        fun setBankID(bankId: Int) {
            datas!!.put(BANK_ID, bankId)
        }

        fun getBankID(): Int? {
            return datas!!.get(BANK_ID) as? Int
        }

        fun setTransID(transId: Int?) {
            datas!!.put(TRANS_ID, transId)
        }

        fun getTransID(): Int? {
            return datas!!.get(TRANS_ID) as? Int
        }

        fun setRefID(refId: String?,context: Context) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(REF_KEY, REF_KEY_REF,refId)
        }

        fun getRefID(context: Context): String? {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(REF_KEY,REF_KEY_REF)
        }

        fun setFormFactor(transId: Int) {
            datas!!.put(FORM_FACTOR, transId)
        }

        fun getFormFactor(): Int? {
            return datas?.get(FORM_FACTOR) as? Int
        }

        fun setSaleType(saletype: String?) {
            datas!!.put(SALE_TYPE, saletype)
        }

        fun getSaleType(): String? {
            return datas?.get(SALE_TYPE) as? String
        }

        fun setAmount(amount: String) {
            datas!!.put(AMOUNT, amount)
        }

        fun getAmount(): String? {
            return datas!!.get(AMOUNT) as? String
        }

        fun setRoc(amount: String) {
            datas!!.put(ROC, amount)
        }

        fun getRoc(): String? {
            return datas!!.get(ROC) as? String
        }

        fun setPoints(amount: String) {
            datas!!.put(POINTS, amount)
        }

        fun getPoints(): String? {
            return datas!!.get(POINTS) as? String
        }

        fun setCardNo(cardNo: String) {
            datas!!.put(CARD_NUM, cardNo)
        }

        fun getCardNo(): String? {
            return datas!!.get(CARD_NUM) as? String
        }

/*
        fun setPayCode(paycode: String) {
            datas!!.put(PAY_CODE, paycode)
        }

        fun getPayCode(): String? {
            return datas!!.get(PAY_CODE) as? String
        }
*/

        fun setPayCode(paycode: String?,context: Context) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(PAY_CODE_KEY, PAY_CODE,paycode)
        }

        fun getPayCode(context: Context): String? {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(PAY_CODE_KEY,PAY_CODE)
        }

        fun setCardType(cardType: String) {
            datas!!.put(CARD_TYPE, cardType)
        }

        fun getCardType(): String? {
            return datas!!.get(CARD_TYPE) as? String
        }

        fun setTransType(transTypeData: String?) {
            datas!!.put(TRANSACTION_TYPE, transTypeData)
        }

        fun getTransType(): String? {
            return datas!!.get(TRANSACTION_TYPE) as? String
        }

        fun setVoidTransType(transTypeData: String?) {
            datas!!.put(VOID_TRANSACTION_TYPE, transTypeData)
        }

        fun getVoidTransType(): String? {
            return datas!!.get(VOID_TRANSACTION_TYPE) as? String
        }

        fun setMicrNo1(micr: String) {
            datas!!.put(MICR_NUMBER, micr)
        }

        fun getMicrNo1(): String {
            return datas!!.get(MICR_NUMBER) as String
        }

        fun setChequeNo1(chq: String) {
            datas!!.put(CHEQUE_NUMBER, chq)
        }

        fun getChequeNo1(): String {
            return datas!!.get(CHEQUE_NUMBER) as String
        }

        fun setMagStripOrChipCardType(boolean: Boolean) {
            status = boolean
        }

        fun getMagStripOrChipCardType(): Boolean {
            return status
        }

        @JvmName("setChequeNumber1")
        fun setChequeNumber(chequeNumberData: String?) {
            datas!!.put(CHEQUE_NUMBER, chequeNumberData)
        }

        @JvmName("getChequeNumber1")
        fun getChequeNumber(): Any? {
            return datas!!.get(CHEQUE_NUMBER)
        }

        @JvmName("setMicrCode1")
        fun setMicrCode(micrCodeData: String?) {
            datas!!.put(MICR_CODE, micrCodeData)
        }

        @JvmName("getMicrCode1")
        fun getMicrCode(): Any? {
            return datas!!.get(MICR_CODE)
        }

        @JvmName("setControlCardNumber1")
        fun setControlCardNumber(controlCardNumberData: String) {
            datas!!.put(CONTROL_CARD_NUMBER, controlCardNumberData)
        }

        @JvmName("getControlCardNumber1")
        fun getControlCardNumber(): String? {
            return datas!!.get(CONTROL_CARD_NUMBER) as? String
        }

/*        @JvmName("setAmountViewModel1")
        fun setAmountViewModel(amountViewModel: AmountViewModel) {
            datas!!.put(AMOUNT_VIEW_MODEL, amountViewModel)
        }

        @JvmName("getAmountViewModel1")
        fun getAmountViewModel(): AmountViewModel? {
            return datas!!.get(AMOUNT_VIEW_MODEL) as? AmountViewModel
        }

        @JvmName("setCardInfoEntity1")
        fun setCardInfoEntity(cardInfoEntity: CardInfoEntity?) {
            datas!!.put(CARD_INFO_ENTITY, cardInfoEntity)
        }

        @JvmName("getCardInfoEntity1")
        fun getCardInfoEntity(): CardInfoEntity? {
            return datas!!.get(CARD_INFO_ENTITY) as? CardInfoEntity
        }*/

        @JvmName("setPinData1")
        fun setPinData(pinData: String) {
            datas!!.put(PIN_DATA, pinData)
        }

        @JvmName("getPinData1")
        fun getPinData(): String? {
            return datas!!.get(PIN_DATA) as? String
        }

        fun setUTRNumber(utrNumber: String) {
            datas!!.put(UTR_NUMBER, utrNumber)
        }

        fun getUTRNumber(): String? {
            return datas!!.get(UTR_NUMBER) as? String
        }

        fun setTokenNumber(tokenNumber: String?) {
            datas!!.put(TOKEN_NUMBER, tokenNumber)
        }

        fun getTokenNumber(): String? {
            return datas!!.get(TOKEN_NUMBER) as? String
        }

        fun setOtpNumber(otpNumber: String) {
            datas?.put(OTP_NUMBER, otpNumber)
        }

        fun getOtpNumber(): String? {
            return datas?.get(OTP_NUMBER) as? String?
        }

        fun setReportType(reportType: String) {
            datas?.put(REPORT_TYPE, reportType)
        }

        fun getReportType(): String? {
            return datas?.get(REPORT_TYPE) as? String?
        }
        //Save Reversal
        fun saveReversal(context: Context,reversalRequest: String):Boolean {
            var checkStoreReversal:Boolean?=null
            val reversalObject: String = Gson().toJson(reversalRequest)
            val sharedPreferences = context!!.applicationContext.getSharedPreferences(
                SHARED_PREFERENCES_REVERSAL, Context.MODE_PRIVATE)
            editorReversal = sharedPreferences.edit()
            editorReversal!!.putString(SHARED_PREFERENCES_KEY_REVERSAL, reversalObject)
            editorReversal!!.commit()
            if(sharedPreferences.getString(SHARED_PREFERENCES_KEY_REVERSAL, "")!=null) {
                checkStoreReversal=true
            }else{
                checkStoreReversal=false
            }
            return checkStoreReversal
        }

        //Save Reversal
        fun saveReversalofPayBack(context: Context,reversalRequest: String):Boolean {
            var checkStoreReversal:Boolean?=null
            val reversalObject: String = Gson().toJson(reversalRequest)
            val sharedPreferences = context!!.applicationContext.getSharedPreferences(
                SHARED_PREFERENCES_REVERSAL_HP_PAYBACK, Context.MODE_PRIVATE)
            editorReversal = sharedPreferences.edit()
            editorReversal!!.putString(SHARED_PREFERENCES_KEY_REVERSAL_HP_PAYBACK, reversalObject)
            editorReversal!!.commit()
            if(sharedPreferences.getString(SHARED_PREFERENCES_KEY_REVERSAL_HP_PAYBACK, "")!=null) {
                checkStoreReversal=true
            }else{
                checkStoreReversal=false
            }
            return checkStoreReversal
        }
        //Getting Reversal
        fun getReversal_payback(context: Context):String {
            val sharedPreferencesReversal = context!!.applicationContext.getSharedPreferences(SHARED_PREFERENCES_REVERSAL_HP_PAYBACK, Context.MODE_PRIVATE)
            val reversaldata = sharedPreferencesReversal.getString(SHARED_PREFERENCES_KEY_REVERSAL_HP_PAYBACK, "")
            return  reversaldata!!
        }

        fun clearReversal_payback(context: Context) {
            val sharedPreferencesReversal = context!!.applicationContext.getSharedPreferences(
                SHARED_PREFERENCES_REVERSAL_HP_PAYBACK, Context.MODE_PRIVATE)
            editorReversal = sharedPreferencesReversal!!.edit()
            editorReversal!!.clear()
            editorReversal!!.apply()
            editorReversal!!.commit()
        }

        //Getting Reversal
        fun getReversal(context: Context):String {
            val sharedPreferencesReversal = context!!.applicationContext.getSharedPreferences(SHARED_PREFERENCES_REVERSAL, Context.MODE_PRIVATE)
            val reversaldata = sharedPreferencesReversal.getString(SHARED_PREFERENCES_KEY_REVERSAL, "")
            return  reversaldata!!
        }
        //Clear Reversal
        fun clearReversal(context: Context) {
            val sharedPreferencesReversal = context!!.applicationContext.getSharedPreferences(SHARED_PREFERENCES_REVERSAL, Context.MODE_PRIVATE)
            editorReversal = sharedPreferencesReversal!!.edit()
            editorReversal!!.clear()
            editorReversal!!.apply()
            editorReversal!!.commit()
        }

        fun setIsProcessControlPin(isProcessCCN: Boolean) {
            datas!!.put(PROCESS_CCN, isProcessCCN)
        }

        fun getIsProcessControlPin(): Boolean? {
            return datas!!.get(PROCESS_CCN) as? Boolean
        }

        fun setSettleTime(batchID: String,settleTime: String,context: Context) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(Constants.SETTLE_TIME,batchID, settleTime)
        }

        fun getSettleTime(batchId: String,context: Context) : String {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(Constants.SETTLE_TIME,batchId)!!
        }


        fun setReversalAnyTxnType(txnType: String,context: Context) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(TYPE_REVERSAL_KEY,TXN_TYPE_REVERSAL,txnType)

        }

        fun getReversalAnyTxnType(context: Context) : String {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(TYPE_REVERSAL_KEY,TXN_TYPE_REVERSAL)!!
        }

        fun setIsRunning(isRunning: String,context: Context?) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(IS_RUNNING_KEY, IS_RUNNING,isRunning)
        }

        fun getIsRunning(context: Context?): String? {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(IS_RUNNING_KEY,IS_RUNNING)
        }

        fun setVoidSaleType(voidsaletype: String,context: Context?) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(VOID_SALE_TYPE_KEY, VOID_SALE_TYPE,voidsaletype)
        }

        fun getVoidSaleType(context: Context?): String? {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(VOID_SALE_TYPE_KEY,VOID_SALE_TYPE)
        }

        fun setSettleType(settleType: String,context: Context?) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(SETTLE_TYPE_KEY, SETTLE_TYPE,settleType)
        }

        fun getSettleType(context: Context?): String? {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(SETTLE_TYPE_KEY, SETTLE_TYPE)
        }

        fun setAutoSettleDateTime(autoSettleDateTime: String?, context: Context) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(Constants.AUTOSETTLEPREF,Constants.AUTOSETTLEDATETIME , autoSettleDateTime.toString())

        }

        fun getAutoSettleDateTime(context: Context) : String {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(Constants.AUTOSETTLEPREF,Constants.AUTOSETTLEDATETIME)!!
        }

        fun setCheckPrinter(printer: String,context: Context) {
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(Constants.PRINTERPREF,Constants.CHECK_PRINT_RECEIPT , printer)
        }

        fun getCheckPrinter(context: Context) : String {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(Constants.PRINTERPREF,Constants.CHECK_PRINT_RECEIPT)!!
        }

        fun setHardPowerFailureState(state: String, context: Context){
            val sharedPref = SharedPreferencesData(context)
            sharedPref.setSharedPreferenceData(Constants.HPF_PREF,Constants.HPF_STATE , state)

        }

        fun getHardPowerFailureState(context: Context) : String {
            val sharedPref = SharedPreferencesData(context)
            return sharedPref.getSharedPreferenceData(Constants.HPF_PREF,Constants.HPF_STATE)!!
        }


    }





}