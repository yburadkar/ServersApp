package com.yb.serversapp.domain.models

interface StatusResponse {
    val apisAndDb: StatusDbAndApis?
    val sites: StatusSites?
}

interface StatusDbAndApis {
    val webApi: ServerStatus?
    val coreApi: ServerStatus?
    val docuwareApi: ServerStatus?
    val webApiDb: ServerStatus?
    val primeDb: ServerStatus?
    val redisDb: ServerStatus?
    val redisQHQApi: ServerStatus?
    val redisQWApi: ServerStatus?
    val redisQPrime: ServerStatus?
}

interface StatusSites {
    val prime: ServerStatus?
    val myUtilita: ServerStatus?
    val utilitaSwitch: ServerStatus?
    val utilitaExtra: ServerStatus?
    val utilitaBusiness: ServerStatus?
    val utilitaMobile: ServerStatus?
}

interface ServerStatus {
    val url: String?
    val responseCode: Int?
    val responseTime: Double?
    val `class`: String?
}