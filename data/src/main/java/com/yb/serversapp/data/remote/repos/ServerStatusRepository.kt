package com.yb.serversapp.data.remote.repos

import com.yb.serversapp.data.remote.ServerStatusService
import com.yb.serversapp.domain.repos.IServerStatusRepository
import com.yb.serversapp.domain.models.StatusResponse
import io.reactivex.Single
import javax.inject.Inject

class ServerStatusRepository @Inject constructor(
    private val serverStatusService: ServerStatusService
) : IServerStatusRepository {

    override fun getServerStatuses(): Single<StatusResponse> {
        return serverStatusService.getServerStatuses().map { it }
    }

}