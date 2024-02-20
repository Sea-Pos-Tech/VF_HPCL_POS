package com.hpcl_paytm.activity.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class ObjGetParentTransTypeDetail {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id :Int = 0
    @SerializedName("TransType")
    var TransType: Int = 0
    @SerializedName("TransName")
    var TransName: String? = null
    @SerializedName("Type")
    var Type: String? = null
    @SerializedName("ServiceStatus")
    var ServiceStatus: Int = 0
    @SerializedName("Carded")
    var Carded: Int = 0
    @SerializedName("Cardless")
    var Cardless: Int = 0
    @SerializedName("NonCarded")
    var NonCarded: Int = 0
    @SerializedName("MinVal")
    var MinVal: Long = 0
    @SerializedName("MaxVal")
    var MaxVal: Int = 0
    @SerializedName("ParentId")
    var ParentId: Int = 0
    @SerializedName("SerialNo")
    var SerialNo: Int = 0
}