package com.hpcl_paytm.activity.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class HpclBatchTransaction {
    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @ColumnInfo(name = "transaction_Id")
    var transaction_Id: String? = null

    @ColumnInfo(name = "transaction_Date")
     var transaction_Date: String? = null

    @ColumnInfo(name = "transaction_Amount")
     var transaction_Amount: String? = null

    @ColumnInfo(name = "batch_Id")
     var batch_Id: Int = 0

    @ColumnInfo(name = "TransactionType")
     var TransactionType: String? = null

    @ColumnInfo(name = "Category")
     var category: String? = null

    @ColumnInfo(name = "product")
    var product: String? = null

    @ColumnInfo(name = "OperatorId")
    var operatorId: String? = null

    @ColumnInfo(name = "lastLogonDate")
    var lastLogonDate: String? = null

    @ColumnInfo(name = "transactionDate")
    var transactionDate: String? = null
}