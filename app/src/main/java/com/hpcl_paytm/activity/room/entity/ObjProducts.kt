package com.hpcl_paytm.activity.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class ObjProducts {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("Id")
    var Id: Int? = null
    @SerializedName("ProductId")
    var ProductId: Int? = null
    @SerializedName("ProductName")
    var ProductName: String? = null
    @SerializedName("ProductStatus")
    var ProductStatus: Int? = null
}