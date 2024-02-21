package com.hpcl_paytm.activity.apicall

import com.hpcl_paytm.activity.model.Response
import com.hpcl_paytm.activity.model.generateToken.GenerateTokenRequest
import com.hpcl_paytm.activity.model.generateToken.GenerateTokenResponse
import retrofit2.http.Body

interface Repository {

    suspend fun getGenerateToken(@Body generateTokenRequest: GenerateTokenRequest): Response<GenerateTokenResponse>
}