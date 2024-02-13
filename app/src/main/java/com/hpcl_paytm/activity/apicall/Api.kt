package com.hpcl_paytm.activity.apicall

import com.hpcl_paytm.activity.model.TokenResponse
import retrofit2.http.POST

interface Api {
    @POST("/api/refresh-token")
    suspend fun refreshAccessToken(): TokenResponse
}