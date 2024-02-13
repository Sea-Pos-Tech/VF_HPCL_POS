package com.hpcl_paytm.activity.apicall

import com.hpcl_paytm.activity.model.TokenResponse

interface Repository {

    suspend fun getToken(): TokenResponse
}