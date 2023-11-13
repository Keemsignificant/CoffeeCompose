package com.chirayut.coffecompose.model

import com.chirayut.coffecompose.R
import com.google.gson.annotations.SerializedName

data class MenuBar(
    @SerializedName("id")
    val menuId: String?,
    @SerializedName("menu_bar")
    val menuBar: String?,
    @SerializedName("menu_route")
    val menuRoute: String?,
    @SerializedName("is_enable")
    val isMenuBarEnable: Boolean?,
    @SerializedName("menu_icon_active")
    val menuIconActive: String?,
    @SerializedName("menu_icon")
    val menuIcon: String?
)