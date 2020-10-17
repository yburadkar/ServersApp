package com.yb.serversapp.models

import android.os.Parcelable
import androidx.annotation.ColorRes
import com.yb.serversapp.R
import com.yb.serversapp.domain.models.ServerStatus
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiServerStatus(
    val type: String,
    val serverName: String,
    val url: String? = null,
    val responseCode: Int? = null,
    val responseTime: Double? = null,
    val classType: String? = null,
) : Parcelable {

    @ColorRes
    fun getStatusColor(): Int = when(classType) {
        "success" -> R.color.green
        "alert high" -> R.color.red
        else -> R.color.black
    }

    constructor(status: ServerStatus): this(
        type = status.type,
        serverName = status.serverName,
        url = status.url,
        responseCode = status.responseCode,
        responseTime = status.responseTime,
        classType = status.classType
    )

}