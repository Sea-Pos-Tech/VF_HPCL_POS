package com.hpcl_paytm.activity.constants

import android.content.Context
import java.util.*

interface Constants {
    object TokenClass {
        @JvmField
        var TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IiIsIm5iZiI6MTYyOTI4MTg5MiwiZXhwIjoxNjI5MjgzNjkyLCJpYXQiOjE2MjkyODE4OTJ9.G5tsWEYKYkm5OsJ-Stuj4YE625sqyYUuCroAWDpX4T4"
    }

    companion object {

        fun getTrace(context: Context?): String? {
            return GlobalMethods.getString("trace", "000001")
        }

        fun setTrace(context: Context?, msg: String?) {
            GlobalMethods.putString("trace", msg)
        }


        const val RUPEEE_SYMBOL = "₹"
        const val VERIFY_PIN = "verifypin"
        const val CHANGE_PIN = "changepin"
        const val CHANGE_TERMINAL_PIN = "changeterminalpin"
        const val UNBLOCK_TERMINAL_PIN = "unblockterminalpin"

        const val NAV_VALUE = "navValue"
        const val NAV_VALUE1 = "navValue1"
        const val ICC = "ICC"
        const val MAG_STRIPE = "MAGSTRIPE"
        const val BY_MOBILE_NUMBER = "BYMOBILENUMBER"
        const val SOURCE_ID = 1
        const val UserId = "string"
        const val LIMITEXCEED = "limitexceed"
        const val PINCHANGE = "PINCHANGE"
        const val HpPayFlag = "hpPayFlag"
        const val DATABASENAME = "TransactionDetails"
        const val MOBILENO = "mobileNumber"
        const val BASE_URL = "http://180.179.222.161/"
        const val MYPREF = "mypreference"
        const val TITLE = "title"

        const val SELECTRANSACTIONTYPE = "selectrasactiontype"
        const val SELECTPROD = "selectedProdName"
        const val LOYALTYDTPNONDTP = "loyaltyDtpNondtp"
        const val CARDEDNONCARDEDPREF = "cardednoncardedpref"
        const val ENTERAMOUNT = "enteramount"
        const val CONTROLCARDNO = "controlcardno"
        const val REDEEMPOINT = "redeempoint"
        const val REDEEMAMOUNT = "redeemamount"
        const val FORMNUMBER = "formnumber"
        const val NUMBEROFCARDS = "numberofcards"
        const val CARDAMOUNTS = "cardamount"
        const val TOKENIDPREF = "tokenidpref"
        const val TOKENDATEPREF = "tokendatepref"
        const val TMKPREF = "tmkpref"
        const val LOGINPREF = "loginpref"
        const val TOKENID = "tokenid"
        const val ANDROIDAGENT = "Terminal"


        const val PREFCONFIG = "prefconfigfile"
        const val BATCHID = "batch_id"
        const val CCMSSALE = "ccmssale"
        const val REGISTRATION_STATUS = "registration_status"
        const val TERMINAL_PIN_BLOCK = "terminal_pin"

        const val TRANSID = "TransId"
        const val TRANSDATE = "TransDate"
        const val TRANSAMOUNT = "TransAmount"
        const val TRANSPRODUCT = "TransProduct"
        const val MERCHANT_NAME = "merchantName"
        const val VEHICLE_NO = "vehicleNo"
        const val CARD_EXP_DATE = "cardExpDate"
        const val BALANCE = "balance"
        const val RSP = "rsp"
        const val VOLUME = "volume"


        const val STATUS_SUCCESS = 1000
        const val STATUS_SUCCESS_HP_PAY = 0

        const val STATUS_TOKEN_EXPIRED = 1006
        const val STATUS_FALSE = 200
        const val STATUS_RECORD_NOT_FOUND = 1001
        const val BATCH = "BATCH"
        const val TERMINAL_ID = "TERMINAL ID"
        const val SERVER_IP = "SERVER IP"
        const val SETTLE_TIME = "SETTLE TIME"
        const val TOKEN_DATE = "TOKEN DATE"

        const val SECOND_SERVER_IP = "SECOND SERVER IP"
        const val TMK_KEY = "TMK"
        //
        const val SALE = "sale"
        const val ALL = "all"
        const val RECHARGE = "recharge"
        const val SIGNUP = "signup"
        const val LOGIN = "login"
        const val TERMINAL_PIN = "terminal_pin"
        const val LOGIN_OPERATOR = "login_operator"
        const val OPERATOR = "operator"
        const val KEY_EXCHANGE = "keyexchange"
        const val KEY_LOGON = "keylogon"
        const val BATCH_SETTLE = "batchsettle"
        const val LOGIN_SCREEN = "loginscreen"
        const val LOGIN_SCREEN_FROM_MAIN = "loginscreenfromMain"

        const val AUTOSETTLEPREF = "autosettlepref"
        const val AUTOSETTLEDATETIME = "autosettledatetime"
        const val PRINTERPREF = "printerpref"
        const val CHECK_PRINT_RECEIPT = "CHECK_PRINT_RECEIPT"

        const val HPF_PREF  = "hardwarepowerfailurepref"
        const val HPF_STATE = "hardwarepowerfailurestate"
        const val HPF_SENDING = "SENDING_DATA"
        const val HPF_RECEIVE = "RECEIVED_DATA"
        const val HPF_STORE = "STORE"
        const val HPF_PRINTING = "PRINTING_RECEIPT"
        const val HPF_PRINTING_DONE = "PRINTING_RECEIPT_DONE"

        // HP Pay
        const val HP_QR_CODE = "QR Code Sale"
        const val ANDROID_AGENT_HP_Pay = "413"
        const val HP_PAY_QR_CODE = "qrCode"
        const val AMOUNT_BURN= "amountburn"
        const val HP_PAY_CREATIONDATETIME = "creationDateTime"


        const val TOKENIDPREF_HP_PAY = "tokenidpref_hp_pay"
        const val TOKENID_HP_PAY = "tokenid_hp_pay"

        const val STATUS_FAILURE = 1001
        const val INVALIDREFERENCENUMBER = 1003
        const val QRcodeExpired = 1005
        const val STATUSPENDING = 1009
        const val  Aborted = 1010

        // encryption decryption

        const val ENCRYPTIVPREF = "encryptivpref"
        const val ENCRYPTKEYPREF = "encryptkeypref"
        const val IV = "iv"
        const val KEY = "key"
        const val PRIVATEKEY = "privatekey"
        const val PUBLICKEY = "publickey"
        const val ENCRYPT_MODE = "encrytmode"
        const val ENCRYPT_MODE_FLAG = "encrytmode_flag"

        const val PLAN_IVPREF = "plan_ivpref"
        const val PLAN_KEYPREF = "plan_keypref"

        const val PLAN_IV = "plan_iv"
        const val PLAN_KEY = "plan_key"

        /* fun getAllTransactionsList() :ArrayList<String> {
             val allTrans: ArrayList<String> = ArrayList()
             allTrans.add(SaleTransactionDetails.CARDSALE.txnName)
             allTrans.add(SaleTransactionDetails.CCMSSALE.txnName)
            // allTrans.add(SaleTransactionDetails.DEALER_CREDIT_SALE.saleName)
             allTrans.add(SaleTransactionDetails.CREDIT_TXN.txnName)
             allTrans.add(SaleTransactionDetails.CCMS_CASHRECHARGE.txnName)
             allTrans.add(SaleTransactionDetails.CCMS_CHEQUERECHARGE.txnName)
             allTrans.add(SaleTransactionDetails.CCMS_NEFTRECHARGE.txnName)
             allTrans.add(SaleTransactionDetails.BALANCE_TRANSFER.txnName)
             return allTrans
         }*/



    }

    object MainUrlChanged {
        @JvmStatic
        var urlChanged = "0"
        @JvmStatic
        var forToken = "0"
        @JvmStatic
        var tokenID = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IiIsIm5iZiI6MTYzMjM3MTY5OSwiZXhwIjoxNjMyMzczNDk5LCJpYXQiOjE2MzIzNzE2OTl9.OChOQf5x8mkEuiWKmQ7G2qHCUf-hdIrbQsh6Zkf6leE"
        //...
    }
}