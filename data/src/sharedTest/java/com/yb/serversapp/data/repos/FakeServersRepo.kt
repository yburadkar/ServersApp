package com.yb.serversapp.data.repos

import com.yb.serversapp.data.repos.FakeServersRepo.ResponseType.SUCCESS
import com.yb.serversapp.data.testdata.ServersData
import com.yb.serversapp.domain.models.ServerStatus
import com.yb.serversapp.domain.repos.IServerStatusRepository
import io.reactivex.Single

object FakeServersRepo : IServerStatusRepository {

    private var responseType: ResponseType = SUCCESS

    override fun getServerStatuses(): Single<List<ServerStatus>> {
        return when (responseType) {
            SUCCESS -> Single.just(ServersData.serversList)
            ResponseType.ERROR -> Single.error(Throwable())
        }
    }

    fun setResponse(type: ResponseType) {
        responseType = type
    }

    enum class ResponseType {
        SUCCESS,
        ERROR
    }

}