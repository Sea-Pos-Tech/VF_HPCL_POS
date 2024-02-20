package com.hpcl_paytm.activity.model.generateToken

import com.google.gson.annotations.SerializedName

data class GenerateTokenRequest(
    @SerializedName("useragent")
    val useragent: String,
    @SerializedName("userid")
    val userid: String,
    @SerializedName("userip")
    val userip: String
)