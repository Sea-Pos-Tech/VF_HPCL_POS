package com.hpcl_paytm.activity.apicall

import com.hpcl_paytm.activity.model.generateToken.GenerateTokenResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: Api
) : Repository {
    override suspend fun getToken(): GenerateTokenResponse = api.refreshAccessToken()
}