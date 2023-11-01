package com.team15.muzimusic.base.network

import com.team15.muzimusic.common.DataLocal

object NetworkHelper {

    fun getAuthorizationHeader(): Map<String, String> {
        val headers = mutableMapOf<String, String>()
//        headers["Content-Type"] = "application/json"
        headers["Authorization"] = "Bearer ${DataLocal.ACCESS_TOKEN}"
        return headers.toMap()
    }

}