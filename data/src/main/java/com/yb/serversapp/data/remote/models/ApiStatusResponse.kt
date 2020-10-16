package com.yb.serversapp.data.remote.models

import com.google.gson.annotations.SerializedName
import com.yb.serversapp.domain.models.ServerStatus
import com.yb.serversapp.domain.models.StatusDbAndApis
import com.yb.serversapp.domain.models.StatusResponse
import com.yb.serversapp.domain.models.StatusSites

class ApiStatusResponse(
    @SerializedName("APIs & DBs")
    override val apisAndDb: ApiStatusDbAndApis? = null,
    @SerializedName("Sites")
    override val sites: ApiStatusSites? = null,
) : StatusResponse

class ApiStatusDbAndApis (
    @SerializedName("Web\u200BAPI")
    override val webApi: ApiServerStatus? = null,
    @SerializedName("Core\u200BAPI")
    override val coreApi: ApiServerStatus? = null,
    @SerializedName("Docuware\u200BAPI")
    override val docuwareApi: ApiServerStatus? = null,
    @SerializedName("WebAPI DB")
    override val webApiDb: ApiServerStatus? = null,
    @SerializedName("Prime DB")
    override val primeDb: ApiServerStatus? = null,
    @SerializedName("Redis DB")
    override val redisDb: ApiServerStatus? = null,
    @SerializedName("Redis Q HQAPI")
    override val redisQHQApi: ApiServerStatus? = null,
    @SerializedName("Redis Q WAPI")
    override val redisQWApi: ApiServerStatus? = null,
    @SerializedName("Redis Q Prime")
    override val redisQPrime: ApiServerStatus? = null,
) : StatusDbAndApis

class ApiStatusSites (
    @SerializedName("Prime")
    override val prime: ApiServerStatus? = null,
    @SerializedName("My Utilita")
    override val myUtilita: ApiServerStatus? = null,
    @SerializedName("Utilita Switch")
    override val utilitaSwitch: ApiServerStatus? = null,
    @SerializedName("Utilita Extra")
    override val utilitaExtra: ApiServerStatus? = null,
    @SerializedName("Utilita Business")
    override val utilitaBusiness: ApiServerStatus? = null,
    @SerializedName("Utilita Mobile")
    override val utilitaMobile: ApiServerStatus? = null,
) : StatusSites

class ApiServerStatus(
    override val url: String? = null,
    override val responseCode: Int? = null,
    override val responseTime: Double? = null,
    override val `class`: String? = null,
) : ServerStatus
