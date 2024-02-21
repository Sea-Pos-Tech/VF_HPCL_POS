package com.hpcl_paytm.activity.apicall

import com.hpcl_paytm.BuildConfig


object Utils {

   // const val MAIN_URL= "https://dtpapitestv1.mloyalcapture.com" ///UAT Url
    const val MAIN_URL= "https://posapi.drivetrackplus.com"//BuildConfig.MAIN_URL
    const val DTP_TEST_URL="https://dtpapitestv1.mloyalcapture.com"
    const val HP_PAY_URL = "https://customerapi.hppay.in"
    const val HP_PAY_TXN_URL = "https://customerapi.mloyalretail.com"
    const val UAT_URL =""// BuildConfig.HP_PAY //Uat Url

    const val GET_GENERATE_TOKEN = "/api/dtplus/generate_token"
    const val HP_PAY_REPORT_URL = ""//BuildConfig.HP_PAY
    const val GET_CCMS_SALE = "/api/dtplus/transaction/sale_by_terminal"
    const val GET_TRACKING = "/api/dtplus/transaction/tracking_by_terminal"
    const val GET_DRIVER_DTP = "/api/dtplus/transaction/insert_driver_loyalty"
    const val GET_CREDIT_SALE = "/api/dtplus/settings/credit_sale"
    const val GET_CCMS_RECHARGESALE = "/api/dtplus/transaction/recharge_ccms_account"
    const val GET_BATCH_SETTLEMENT = "/api/dtplus/transaction/check_transcations_for_batch_settlement"
    const val GET_BATCH_UPLOAD = "/api/dtplus/transaction/batch_upload"
    const val GET_CARD_FEE_PAYMENT = "/api/dtplus/transaction/card_fee_payment"
    const val GET_BALANCE_TRANSFER = "/api/dtplus/transaction/balance_transfer"
    const val GET_OTP = "/api/dtplus/transaction/generate_otp"
    //const val GET_REGISTRATION = "/api/dtplus/transaction/get_registration_parameters_for_comco_merchant"
    const val GET_REGISTRATION = "/api/dtplus/transaction/get_registration_parameters"
    const val GET_RELOAD = "/api/dtplus/transaction/reload_account"
    const val GET_BALANCE_ENQUIRY = "/api/dtplus/transaction/balance_enquiry"
    const val GET_HPPAY_GET_TXN_RECEIPT= "/api/hppay/transaction/gettransactionreceipt"
    const val GET_HPPAY_GET_TXN_RECEIPT_NEW= "/api/hppay/transaction/gettransactionreceipt_by_transactionid"
    const val GET_HPPAY_SUMMARY_RECEIPT= "/api/hppay/transaction/gettransactionsummary"

    const val GET_CCMS_BALANCE_ENQUIRY = "/api/dtplus/transaction/ccms_balance_enquiry"
    const val GET_UNBLOCK_CHECK_SATUS ="/api/dtplus/transaction/pin_unblock_request"
    const val GET_UNBLOCK_CARD_PIN ="/api/dtplus/transaction/unblock_card_pin"
    const val GET_CHANGE_CARD_PIN = "/api/dtplus/transaction/change_card_pin"
    const val GET_CONTROL_CHANGE_PIN = "/api/dtplus/transaction/control_card_pin_change"
    const val GET_CARD_UNBLOCKING = "/api/dtplus/transaction/unblock_card_pin_through_ccn"
    const val GET_CARD_UNBLOCKING_STATUS = "/api/dtplus/transaction/unblock_cardpin_checkstatus_through_ccn"
    const val GET_UPDATE = "/api/dtplus/transaction/get_terminal_app_update"
    const val GET_FAST_TAG_GET_OTP = "/api/dtplus/Fastag/fastag_get_otp"
    const val GET_CONFIRM_FAST_TAG_GET_OTP = "/api/dtplus/Fastag/fastag_confirm_otp"
    const val GET_KEY_EXCHANGE = "/api/dtplus/transaction/hms_key_exchange"
    const val GET_LOGON = "/api/dtplus/transaction/log_on"
    const val GET_VOID = "/api/dtplus/transaction/void_financial_transactions"
    const val GET_REVERSAL = "/api/dtplus/transaction/reversal_financial_transactions"
    const val GET_LOYALTY_BALANCE = "/api/dtplus/transaction/loyalty_balance_check"
    const val GET_ANY_TXN_SLIP = "/api/dtplus/transaction/get_duplicate_transaction"
    const val GET_TERMINAL_PIN_STATUS ="/api/dtplus/terminal/update_terminal_unblock_terminal_pin"
    const val GET_CARD_FEE_AMOUNT ="/api/dtplus/transaction/get_card_fee_amount_with_form_number"

    //Hp Pay Txn Urls
    const val GET_QR_SALE = "/api/hppay/dtppos/dpSaleByQrCode"
    const val GET_GENERATE_TOKEN_HP_PAY = "/api/hppay/generate_imsl_token"
    const val GET_PAY_CODE_SALE = "/api/hppay/dtppos/dpProcessPaycode"
    const val GET_PAY_Status = "/api/hppay/dtppos/CheckQRCodeTransactionStatus"
    const val GET_OTP_HP_PAY = "/api/hppay/dtppos/dpInitiateSale"
    const val GET_AUTH_OTP_SALE = "/api/hppay/dtppos/dpAuthorizeSale"

   //hp pay payback URLS
    const val GET_PAYBACK_EARN = "/api/hppay/dtppos/dpearnpbpoint"
    const val GET_PAYBACK_POINT = "/api/hppay/dtppos/dpgetpbbalance"
    const val GET_PAYBACK_EARN_REVERSAL = "/api/hppay/dtppos/dpearnpbpoint"
    const val GET_PAYBACK_BURN = "/api/hppay/dtppos/dpburnpbpoint"
    const val GET_PAYBACK_BURN_REVERSAL = "/api/hppay/dtppos/dpburnreversalpbpoint"




}