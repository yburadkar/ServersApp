package com.yb.serversapp.data.remote

import com.yb.serversapp.data.remote.models.ApiStatusResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ServerStatusService {

    @GET("status")
    fun getServerStatuses(): Single<ApiStatusResponse>

}