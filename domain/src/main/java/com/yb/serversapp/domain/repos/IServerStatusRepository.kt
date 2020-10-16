package com.yb.serversapp.domain.repos

import com.yb.serversapp.domain.models.StatusResponse
import io.reactivex.Single

interface IServerStatusRepository {
    fun getServerStatuses(): Single<StatusResponse>
}