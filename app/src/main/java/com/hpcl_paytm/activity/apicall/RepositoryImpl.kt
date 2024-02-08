package com.hpcl_paytm.activity.apicall

import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: Api
): Repository {

}