package com.hpcl_paytm.activity.model

sealed class Response<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : Response<T>(data = data)
    class Error<T>(errorMessage: String) : Response<T>(message = errorMessage)

}
