package com.hpcl_paytm.activity.constants

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.hpcl_paytm.activity.encryption_decryption.AesDesWrapper
import java.lang.Exception
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.security.SecureRandom
import java.util.*


class AppUtils {

    companion object {

        public var IV: ByteArray? = generateIv()
        public var PASSWORD: String= "AB737CC29DFG7C80644A5B01544CBA72"
        //adding 0 as prefix
        fun addPreFixer(s: String?, requiredLength: Int): String? {
            //int iteration=requiredLength
            var s = s
            val sBuilder = StringBuilder(s)
            while (sBuilder.length < requiredLength) {
                sBuilder.insert(0, "0")
            }
            s = sBuilder.toString()
            return s
        }

    /*    fun getTitleName(saleType: String,context: Context) : String {
            var transList=getTransactionList(context)
            if (transList!=null&&transList.size>0) {
                for (j in transList.indices) {
                    if (transList[j].TransName?.trim().equals(saleType)) {
                        return "SALE(CCMS)"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "SALE(CARD)"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "SALE(CREDIT TXN)"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "SALE(DEALER CREDIT)"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "SALE(FASTTAG)"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "CASH RELOAD"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "CHEQUE RELOAD"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "CCMS RELOAD"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "CCMS RECHARGE(CASH)"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "CCMS RECHARGE(CHEQUE)"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "CCMS RECHARGE(NEFT/RTGS)"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "CARD FEE(DTP)"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "CARD BALANCE"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "CCMS BAL(CCMS CASH)"
                    } else if (transList[j].TransName?.trim().equals(saleType)) {
                        return "TRACKING"
                    }
                }
            }
            *//*when(saleType) {
                SaleTransactionDetails.CCMSSALE.txnName -> { return "SALE(CCMS)" }
                SaleTransactionDetails.CARDSALE.txnName -> { return "SALE(CARD)" }
                SaleTransactionDetails.DEALER_CREDIT_SALE.txnName -> { return "SALE(CREDIT TXN)" }
                SaleTransactionDetails.CREDIT_TXN.txnName -> { return "SALE(DEALER CREDIT)" }
                SaleTransactionDetails.FASTAG_SALE_ONLY_CARDLESS_MOBILE.txnName -> { return "SALE(FASTTAG)" }
                SaleTransactionDetails.CASH_RELOAD.txnName -> {  return SaleTransactionDetails.CASH_RELOAD.txnName }
                SaleTransactionDetails.CHEQUE_RELOAD.txnName -> { return SaleTransactionDetails.CHEQUE_RELOAD.txnName }
                SaleTransactionDetails.CCMS_RELOAD.txnName -> { return SaleTransactionDetails.CCMS_RELOAD.txnName  }
                SaleTransactionDetails.CCMS_CHEQUERECHARGE.txnName -> { return "CCMS RECHARGE(CHEQUE)" }
                SaleTransactionDetails.CCMS_NEFTRECHARGE.txnName -> { return "CCMS RECHARGE(NEFT/RTGS)" }
                SaleTransactionDetails.CCMS_CASHRECHARGE.txnName -> { return "CCMS RECHARGE(CASH)" }
                SaleTransactionDetails.CARD_FEE_PAYMENT.txnName -> { return "CARD FEE(DTP)" }
                SaleTransactionDetails.BALANCE_ENQUIRY.txnName -> { return "CARD BALANCE" }

                SaleTransactionDetails.CCMS_BALANCE.txnName -> { return "CCMS BAL(CCMS CASH)"}
                SaleTransactionDetails.TRACKING.txnName -> { return "TRACKING"}

            }*//*
            return " "

        }
      //Registration Trans list
        fun getTransactionList(context:Context):List<ObjGetRegistrationProcessTrans> {
            var appRepository:AppRepository
            appRepository= AppRepository(context)
            var list:List<ObjGetRegistrationProcessTrans> = Collections.emptyList()
            list=appRepository?.getTransList()!!
            return list
        }*/

        //Getting Application Version Name
        fun getAppVersionName(context: Context):String{
            var version:String?=null
            try {
                 val pInfo: PackageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0)
                 version = pInfo.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return version!!
        }

        /**
         * write Data Key
         * @throws PedDevException exception
         */

        @Throws(NoSuchAlgorithmException::class, NoSuchProviderException::class)
         fun generateIv(): ByteArray? {
            val random = SecureRandom.getInstance(AesDesWrapper.RANDOM_ALGORITHM)
            val iv = ByteArray(AesDesWrapper.IV_LENGTH)
            random.nextBytes(iv)
            return iv
        }

    }
}