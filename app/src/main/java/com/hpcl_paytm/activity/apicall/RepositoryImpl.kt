package com.hpcl_paytm.activity.apicall

import com.hpcl_paytm.activity.model.Response
import com.hpcl_paytm.activity.model.generateToken.GenerateTokenRequest
import com.hpcl_paytm.activity.model.generateToken.GenerateTokenResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: APIService
) : Repository {
    override suspend fun getGenerateToken(generateTokenRequest: GenerateTokenRequest): Response<GenerateTokenResponse> {
        return api.getGenerateToken(generateTokenRequest)
    }

}