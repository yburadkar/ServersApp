package com.yb.serversapp.features.status

import com.yb.serversapp.domain.models.ServerStatus

data class UiServerStatus(
    val type: String,
    val serverName: String,
    val url: String? = null,
    val responseCode: Int? = null,
    val responseTime: Double? = null,
    val classType: String? = null,
) {
    constructor(status: ServerStatus): this(
        type = status.type,
        serverName = status.serverName,
        url = status.url,
        responseCode = status.responseCode,
        responseTime = status.responseTime,
        classType = status.classType
    )
}