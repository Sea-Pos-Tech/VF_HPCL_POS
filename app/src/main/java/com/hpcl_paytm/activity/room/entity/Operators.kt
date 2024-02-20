package com.hpcl_paytm.activity.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Operators {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("operatorId")
    var operatorId: String? = null
    @SerializedName("password")
    var password: String? = null
    @SerializedName("isLogon")
    var isLogon = false
    @SerializedName("lastLogonDate")
    var lastLogonDate: String? = null
    @SerializedName("lastLogonTime")
    var lastLogonTime: String? = null
}