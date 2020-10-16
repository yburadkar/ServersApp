package com.yb.serversapp.domain.repos

import com.yb.serversapp.domain.models.ServerStatus
import io.reactivex.Single

interface IServerStatusRepository {
    fun getServerStatuses(): Single<List<ServerStatus>>
}