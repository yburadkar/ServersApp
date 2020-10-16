package com.yb.serversapp.data.remote.repos

import com.yb.serversapp.data.remote.ServerStatusService
import com.yb.serversapp.data.remote.models.RemoteServerStatus
import com.yb.serversapp.domain.models.ServerStatus
import com.yb.serversapp.domain.repos.IServerStatusRepository
import io.reactivex.Single
import javax.inject.Inject

class ServerStatusRepository @Inject constructor(
    private val serverStatusService: ServerStatusService
) : IServerStatusRepository {

    override fun getServerStatuses(): Single<List<ServerStatus>> {
        return serverStatusService.getServerStatuses().map { RemoteServerStatus.from(it) }
    }

}