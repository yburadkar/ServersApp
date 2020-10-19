package com.yb.serversapp.models

import android.content.Context
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

    constructor(status: ServerStatus): this(
        type = status.type,
        serverName = status.serverName,
        url = status.url,
        responseCode = status.responseCode,
        responseTime = status.responseTime,
        classType = status.classType
    )

    @ColorRes
    fun getStatusColor(): Int = when(classType) {
        "success" -> R.color.green
        "alert high" -> R.color.red
        else -> R.color.black
    }

    fun url(context: Context): String = url ?: context.getString(R.string.url_null_text)

    fun time(context: Context): String = responseTime?.toString() ?: context.getString(R.string.time_null_text)

    fun classType(context: Context): String = classType ?: context.getString(R.string.class_null_text)

    fun statusCode(context: Context): String = responseCode?.toString() ?: context.getString(R.string.code_null_text)

}