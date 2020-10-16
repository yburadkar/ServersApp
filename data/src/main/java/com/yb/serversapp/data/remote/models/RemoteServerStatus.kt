package com.yb.serversapp.data.remote.models

import com.yb.serversapp.domain.models.ServerStatus

class RemoteServerStatus(
    override val type: String,
    override val serverName: String,
    override val url: String? = null,
    override val responseCode: Int? = null,
    override val responseTime: Double? = null,
    override val classType: String? = null,
) : ServerStatus {

    companion object {
        fun from(statusMap: Map<String, Map<String, ApiServerStatus>>) : List<RemoteServerStatus> {
            val statuses = mutableListOf<RemoteServerStatus>()
            for(key in statusMap.keys) {
                val servers = statusMap[key] ?: emptyMap()
                for(server in servers) statuses.add(
                    RemoteServerStatus(
                        type = key,
                        serverName = server.key,
                        url = server.value.url,
                        responseCode = server.value.responseCode,
                        responseTime = server.value.responseTime,
                        classType = server.value.`class`,
                    )
                )
            }
            return statuses
        }
    }
}