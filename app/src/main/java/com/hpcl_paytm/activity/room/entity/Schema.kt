package com.hpcl_paytm.activity.room.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Schema(
        var schemaId: Int? = null,
        var status: Int? = null,
        var order: Int? = null,
        var schemaName: String? = null,
        var transType: Int? = null,
        var implementationDate: String? = null,
        var expiryDate: String? = null,
        var allowedCurrencyIDs: String? = null,
        var allowedServiceIDs: String? = null,
        var perTransMinValue: Int? = null,
        var perTransMaxValue: Int? = null,
        var perTransIncrements: Int? = null,
        var offlineMaxValue: Int? = null,
        var numOfFollowUpTxn: Int? = null,
        var txnType: Int? = null,
        var currencyType: Int? = null,
        var unknown: String? = null, ) {
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
}