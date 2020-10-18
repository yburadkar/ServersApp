package com.yb.serversapp.utils

import com.yb.serversapp.domain.models.ServerStatus

fun ServerStatus.isEqual(status: ServerStatus): Boolean {
    return with(status) {
        (this.classType == classType) && (this.responseCode == responseCode) && (this.responseTime == responseTime) &&
                (this.serverName ==serverName) && (this.type == type) && (this.url == url)
    }
}