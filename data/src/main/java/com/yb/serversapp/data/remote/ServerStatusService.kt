package com.yb.serversapp.data.remote

import com.yb.serversapp.data.remote.models.ApiServerStatus
import io.reactivex.Single
import retrofit2.http.GET

interface ServerStatusService {

    @GET("status")
    fun getServerStatuses(): Single<Map<String, Map<String, ApiServerStatus>>>

}