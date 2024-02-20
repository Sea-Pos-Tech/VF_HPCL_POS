package com.hpcl_paytm.activity.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class HpclTransaction {
    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @ColumnInfo(name = "transaction_Id")
    var transaction_Id: String? = null

    @ColumnInfo(name = "transaction_Date")
     var transaction_Date: String? = null

    @ColumnInfo(name = "transaction_Amount")
     var transaction_Amount: String? = null

    @ColumnInfo(name = "product")
     var product: String? = null

    @ColumnInfo(name = "batch_Id")
     var batch_Id: Int = 0

    @ColumnInfo(name = "productType")
     var productType: String? = null

    @ColumnInfo(name = "TransactionType")
     var TransactionType: String? = null

    @ColumnInfo(name = "mobileNo")
     var mobileNo: String? = null

    @ColumnInfo(name = "cardNumber")
     var cardNumber: String? = null

    @ColumnInfo(name = "cardName")
     var cardName: String? = null

    @ColumnInfo(name = "vehicleNumber")
     var vehicleNumber: String? = null

    @ColumnInfo(name = "expDate")
     var expDate: String? = null

    @ColumnInfo(name = "odometer")
     var odometerNumber: String? = null

    @ColumnInfo(name = "cardType")
     var cardType: String? = null

    @ColumnInfo(name = "controlCardNum")
     var controlCardNum: String? = null

    @ColumnInfo(name = "chequeNum")
     var chequeNum: String? = null

    @ColumnInfo(name = "micrCode")
     var micrCode: String? = null

    @ColumnInfo(name = "formNum")
     var formNum: String? = null

    @ColumnInfo(name = "cardsNum")
     var numOfCards: String? = null

    @ColumnInfo(name = "UTRNum")
     var UTRNum: String? = null

    @ColumnInfo(name = "Category")
     var category: String? = null

    @ColumnInfo(name = "OperatorId")
    var operatorId: String? = null

    @ColumnInfo(name = "Balance")
    var balanceAmount: String? = null

    @ColumnInfo(name = "Token Number")
    var tokenNumber: String? = null

    @ColumnInfo(name = "RSP")
    var rsp: String? = null

    @ColumnInfo(name = "Volume")
    var volume: String? = null

    @ColumnInfo(name = "Stan")
    var stan: String? = null

    @ColumnInfo(name = "void_transaction_Id")
    var void_transaction_Id: String? = null

    @ColumnInfo(name = "RefNo")
    var reference_number: String? = null

    @ColumnInfo(name = "transactionDate")
    var transactionDate: String? = null

    @ColumnInfo(name = "FinanceChargesValue")
    var financeChargesValue: String? = null

}