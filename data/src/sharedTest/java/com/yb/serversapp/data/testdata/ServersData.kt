package com.yb.serversapp.data.testdata

import com.yb.serversapp.data.remote.models.RemoteServerStatus

object ServersData {

    val serversList = listOf<RemoteServerStatus>(
        RemoteServerStatus(type = "APIs", serverName = "A", url = "url", responseCode = 200, responseTime = 0.041312567, classType = "success"),
        RemoteServerStatus(type = "APIs", serverName = "B", url = null, responseCode = 200, responseTime = 0.045234567, classType = "success"),
        RemoteServerStatus(type = "APIs", serverName = "C", url = "url", responseCode = 500, responseTime = 0.045645457, classType = "alert high"),
        RemoteServerStatus(type = "Sites", serverName = "D", url = "url", responseCode = 500, responseTime = 0.0455476567, classType = "alert high"),
        RemoteServerStatus(type = "Sites", serverName = "E", url = null, responseCode = 200, responseTime = 0.0413567, classType = "success"),
    )

}