package com.hpcl_paytm.activity.apicall

import com.hpcl_paytm.activity.MyApplication
import com.hpcl_paytm.activity.constants.Constants
import com.hpcl_paytm.activity.constants.GlobalMethods
import com.hpcl_paytm.activity.model.generateToken.GenerateTokenRequest
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor : Interceptor {
    // Other interceptor configuration and properties

    @Inject
    lateinit var repository: Repository

    private val sessionManager = SessionManager() // Replace with your session management class

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val accessToken = sessionManager.getAccessToken()

        if (accessToken != "" && sessionManager.isAccessTokenExpired()) {

            val tokenRequestData = GenerateTokenRequest(
                Constants.ANDROIDAGENT,
                GlobalMethods.getTerminalId(MyApplication.appContext!!).toString(),
                GlobalMethods.getDeviceId(MyApplication.appContext!!)
            )
            // Make the token refresh request
            val refreshedToken = runBlocking {
                val response = repository.getGenerateToken(tokenRequestData)
                // Update the refreshed access token and its expiration time in the session
                sessionManager.updateAccessToken(
                    response.data?.Token.toString(),
                    response.data?.ExpiryTime?.toLong()!!
                )
                response.data.Token
            }

            if (refreshedToken != null) {
                // Create a new request with the refreshed access token
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $refreshedToken")
                    .build()

                // Retry the request with the new access token
                return chain.proceed(newRequest)
            }
        }

        // Add the access token to the request header
        val authorizedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()

        return chain.proceed(authorizedRequest)
    }
}