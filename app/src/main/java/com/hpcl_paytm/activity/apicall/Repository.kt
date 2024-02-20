package com.hpcl_paytm.activity.apicall

import com.hpcl_paytm.activity.model.generateToken.GenerateTokenResponse

interface Repository {

    suspend fun getToken(): GenerateTokenResponse
}