package com.hpcl_paytm.activity.apicall

class SessionManager {

    private var accessToken: String? = null
    private var accessTokenExpirationTime: Long? = null

    // Method to check if the access token has expired
    fun isAccessTokenExpired(): Boolean {
        val currentTimeMillis = System.currentTimeMillis()
        return accessTokenExpirationTime != null && currentTimeMillis >= accessTokenExpirationTime!!
    }

    // Method to update the access token and its expiration time in the session
    fun updateAccessToken(token: String, expiresIn: Long) {
        accessToken = token
        accessTokenExpirationTime =
            System.currentTimeMillis() + expiresIn * 1000 // Convert expiresIn to milliseconds
    }

    fun getAccessToken(): String {
        return accessToken ?: ""
    }
}