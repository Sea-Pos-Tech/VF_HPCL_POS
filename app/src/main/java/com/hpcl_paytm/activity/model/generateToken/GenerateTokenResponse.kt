package com.hpcl_paytm.activity.model.generateToken

import com.google.gson.annotations.SerializedName

data class GenerateTokenResponse(
    @SerializedName("Message")
    val Message: String,
    @SerializedName("Method_Name")
    val Method_Name: String,
    @SerializedName("Model_State")
    val Model_State: ModelState,
    @SerializedName("Status_Code")
    val Status_Code: Int,
    @SerializedName("Success")
    val Success: Boolean,
    @SerializedName("Token")
    val Token: String,
    @SerializedName("Internel_Status_Code")
    val Internel_Status_Code: Int,
    @SerializedName("ExpiryTime")
    val ExpiryTime: String
)
class ModelState