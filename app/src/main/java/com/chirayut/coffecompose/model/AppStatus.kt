package com.chirayut.coffecompose.model

import com.google.gson.annotations.SerializedName

data class AppStatus(
    @SerializedName("current_app_version")
    val menuId: String,
    @SerializedName("is_force_update")
    val menuName: Boolean,
    @SerializedName("is_maintenance")
    val menuDesc: Boolean
)