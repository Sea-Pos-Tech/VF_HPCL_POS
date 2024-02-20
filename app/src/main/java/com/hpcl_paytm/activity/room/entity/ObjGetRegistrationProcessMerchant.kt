package com.hpcl_paytm.activity.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity

class ObjGetRegistrationProcessMerchant {
    @SerializedName("MerchantId")
    @ColumnInfo(name = "MerchantId")
    var MerchantId: String? = null
    @SerializedName("TerminalId")
    @PrimaryKey(autoGenerate = true)
    var TerminalId: Long? = 0
    @SerializedName("MerchantName")
    @ColumnInfo(name = "MerchantName")
    var MerchantName: String? = null
    @SerializedName("CommunicationCity")
    @ColumnInfo(name = "CommunicationCity")
    var CommunicationCity: String? = null
    @SerializedName("Header1")
    @ColumnInfo(name = "Header1")
    var Header1: String? = null
    @SerializedName("Header2")
    @ColumnInfo(name = "Header2")
    var Header2: String? = null
    @ColumnInfo(name = "Footer1")
    @SerializedName("Footer1")
    var Footer1: String? = null
    @SerializedName("Footer2")
    @ColumnInfo(name = "Footer2")
    var Footer2: String? = null
    @ColumnInfo(name = "BatchSaleLimit")
    @SerializedName("BatchSaleLimit")
    var BatchSaleLimit: String? = null
    @SerializedName("BatchReloadLimit")
    @ColumnInfo(name = "BatchReloadLimit")
    var BatchReloadLimit: String? = null
    @SerializedName("BatchSize")
    @ColumnInfo(name = "BatchSize")
    var BatchSize: String? = null
    @ColumnInfo(name = "SettlementTime")
    @SerializedName("SettlementTime")
    var SettlementTime: String? = null
    @ColumnInfo(name = "RemoteDownload")
    @SerializedName("RemoteDownload")
    var RemoteDownload: String? = null
    @ColumnInfo(name = "URL")
    @SerializedName("URL")
    var URL: String? = null
    @ColumnInfo(name = "BatchNo")
    @SerializedName("BatchNo")
    var BatchNo: String? = null
    @ColumnInfo(name = "CardFeeAmount")
    @SerializedName("CardFeeAmount")
    var CardFeeAmount: String? = null
    @ColumnInfo(name = "TerminalResponseTimeOut")
    @SerializedName("TerminalResponseTimeOut")
    var TerminalResponseTimeOut:String?=null
    @SerializedName("ServerTime")
    @ColumnInfo(name = "ServerTime")
    var ServerTime:String?=null
    @SerializedName("Name")
    @ColumnInfo(name = "Name")
    var Name:String?=null
}