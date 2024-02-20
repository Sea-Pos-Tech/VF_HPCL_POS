package com.hpcl_paytm.activity.constants

import android.util.Log

object Validation {
    fun isValidEnterAmount(amount: String): Boolean {
        var amount = amount
        Log.e("Amount=", amount.length.toString() + "")
        amount = amount.trim { it <= ' ' }
        return !amount.trim { it <= ' ' }
            .equals("", ignoreCase = true) && !amount.trim { it <= ' ' }
            .equals("0.00", ignoreCase = true)
    }

    /**
     * It is a method used for verify mobile number digits
     * @param mobNo
     * @return
     */
    fun isValidMobileNo(mobNo: String): Boolean {
        return mobNo.length == 10 && !mobNo.startsWith("0")
    }

    /**
     * It is a method used for verify OTP length which is valid or not
     * @param otp
     * @return
     */
    fun isValidOtp(otp: String?): Boolean {
        return otp != null && otp.length == 4
    }

    /**
     * This method is used for mobile number ten digit validate and also validate not entering null value
     * @param mobNo
     * @return
     */
    fun isvalidMobNo(mobNo: String?): Boolean {
        return mobNo != null && mobNo.trim { it <= ' ' }.length == 10 && mobNo.trim { it <= ' ' }[0] != '0'
    }

    /**
     * This method is used for validate paycode in this project which is more than 5 digits and less than 16 digits
     * @param payCodeno
     * @return
     */
    fun isvalidpaycode(payCodeno: String?): Boolean {
        return payCodeno != null && payCodeno.trim { it <= ' ' }.length > 5
    }

    /**
     * This method is used for validate IAC ID in this project which equalt to 16 digits
     * @param IAC ID
     * @return
     */
    fun isvalidIacId(iacId: String?): Boolean {
        return iacId != null && iacId.trim { it <= ' ' }.length > 15
    }

    fun isvalidpaycodeEgv(payCodeno: String?): Boolean {
        return payCodeno != null && payCodeno.trim { it <= ' ' }.length > 8
    }


    /**
     * This method is used for token validation and return boolean values
     * @param tokenNo
     * @return
     */
    fun isvalidTokenNo(tokenNo: String): Boolean {
        return tokenNo.trim { it <= ' ' }.length > 4 || tokenNo.trim { it <= ' ' }.length == 8
    }

    /**
     * This method is used for entering Control Card Number is valid or not
     * @param cardNo
     * @return
     */
    fun isvalidCTRLCardNO(cardNo: String?): Boolean {
        var retValue = false
        if (cardNo != null && cardNo.trim { it <= ' ' }.length >= 10) {
            retValue = true
        }
        return retValue
    }

    /**
     * This method is used for validate control card pin  validation
     * @param controlPin
     * @return
     */
    fun isvalidControlPin(controlPin: String?): Boolean {
        var retValue = false
        if (controlPin != null && controlPin.trim { it <= ' ' }.length == 4) {
            retValue = true
        }
        return retValue
    }

    /**
     * This method is used for checking the length of control card pin entered
     */
    fun isvalidCardPin(cardPin: String?): Boolean {
        var retValue = false
        if (cardPin != null && cardPin.trim { it <= ' ' }.length == 4) {
            retValue = true
        }
        return retValue
    }

    fun isvalidVehicleNo(vehicelNo: String?): Boolean {
        var retValue = false
        if (vehicelNo != null && vehicelNo.trim { it <= ' ' }.length == 4) {
            retValue = true
        }
        return retValue
    }

    /**
     * This  method is used for validate Terminal pin entered length is valid or not
     * @param terminalPin
     * @return
     */
    fun isvalidTerminalPin(terminalPin: String?): Boolean {
        var retValue = false
        if (terminalPin != null && terminalPin.trim { it <= ' ' }.length == 4) {
            retValue = true
        }
        return retValue
    }

    /**
     * This method is used for UTR number validation
     * @param utrNo
     * @return
     */
    fun isvalidUTRNo(utrNo: String?): Boolean {
        var retValue = false
        if (utrNo != null && utrNo.trim { it <= ' ' }.length == 16) {
            retValue = true
        }
        return retValue
    }

    /**
     * This method is used  for not entering null value in redeem points
     * @param redeemPoint
     * @return
     */
    fun isvalidRedeemPoint(redeemPoint: String?): Boolean {
        var retValue = false
        if (redeemPoint != null && !redeemPoint.equals("", ignoreCase = true)) {
            val rdmpoint: Int
            rdmpoint = redeemPoint.toInt()
            if (rdmpoint > 0) {
                retValue = true
            }
        }
        return retValue
    }

    /**
     * This method is used  for valid form number checking
     * @param formNo
     * @return
     */
    fun isvalidFormNo(formNo: String?): Boolean {
        var retValue = false
        if (formNo != null && formNo.trim { it <= ' ' }.length == 10) {
            retValue = true
        }
        return retValue
    }

    /**
     * This method is  used for validating correct cheque number entered or not
     * @param chequeNo
     * @return
     */
    fun isvalidChequeNo(chequeNo: String?): Boolean {
        var retValue = false
        if (chequeNo != null && chequeNo.trim { it <= ' ' }.length >= 6) {
            retValue = true
        }
        return retValue
    }

    /**
     * This method  is used for entering  valid roc number
     * @param chequeNo
     * @return
     */
    fun isValidRocNo(chequeNo: String?): Boolean {
        var retValue = false
        if (chequeNo != null && chequeNo.trim { it <= ' ' }.length > 0) {
            retValue = true
        }
        return retValue
    }

    /**
     * This  method  is used for entering correct micr code number
     * @param mICRCode
     * @return
     */
    fun isvalidMICRCode(mICRCode: String?): Boolean {
        var retValue = false
        if (mICRCode != null && mICRCode.trim { it <= ' ' }.length == 9) {
            retValue = true
        }
        return retValue
    }

    /**
     * This method is used for checking valid OTP number entering
     * @param oTPNo
     * @return
     */
    fun isvalidOTPNo(oTPNo: String?): Boolean {
        var retValue = false
        if (oTPNo != null && oTPNo.trim { it <= ' ' }.length >= 4) {
            retValue = true
        }
        return retValue
    }

    /**
     * This method is used for checking valid OTP number entering
     * @param oTPNo
     * @return
     */
    fun isvalidOTPNoFastagSale(oTPNo: String?): Boolean {
        var retValue = false
        if (oTPNo != null && oTPNo.trim { it <= ' ' }.length == 6) {
            retValue = true
        }
        return retValue
    }

    /**
     * This method is used for checking  correct odometer reading
     * @param odometerNo
     * @return
     */
    fun isvalidOdometerReading(odometerNo: String?): Boolean {
        var retValue = false
        if (odometerNo != null && odometerNo.trim { it <= ' ' }.length == 2) {
            retValue = true
        }
        return retValue
    }

    /**
     * This method is used for validate redeem point entered
     * @param redeemPoint
     * @return
     */
    fun isValidRedeemPoints(redeemPoint: String?): Boolean {
        var retValue = false
        if (redeemPoint != null && redeemPoint.trim { it <= ' ' }.length == 5) {
            retValue = true
        }
        return retValue
    }

    fun isValidTerminalId(terminalId: String?): Boolean {
        return terminalId != null && terminalId.trim { it <= ' ' }.length == 10
    }
}