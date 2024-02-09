package com.hpcl_paytm.activity.apicall

import com.hpcl_paytm.activity.model.TokenResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: Api
) : Repository {
    override suspend fun getToken(): TokenResponse = api.refreshAccessToken()
}