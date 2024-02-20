package com.hpcl_paytm.activity.apicall


import com.hpcl_paytm.activity.model.Response
import com.hpcl_paytm.activity.model.generateToken.GenerateTokenRequest
import com.hpcl_paytm.activity.model.generateToken.GenerateTokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface APIService {

    @POST(Utils.GET_GENERATE_TOKEN)
    fun getGenerateToken(@Body generateTokenRequest: GenerateTokenRequest): Response<GenerateTokenResponse>
}