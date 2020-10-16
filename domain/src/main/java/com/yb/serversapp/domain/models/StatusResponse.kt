package com.yb.serversapp.domain.models

interface ServerStatus {
    val type: String
    val serverName: String
    val url: String?
    val responseCode: Int?
    val responseTime: Double?
    val classType: String?
}