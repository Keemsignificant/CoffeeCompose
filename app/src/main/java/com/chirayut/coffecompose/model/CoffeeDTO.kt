package com.chirayut.coffecompose.model

import com.google.gson.annotations.SerializedName

data class CoffeeDTO(
    @SerializedName("id")
    val menuId: String?,
    @SerializedName("menu_name")
    val menuName: String?,
    @SerializedName("menu_desc")
    val menuDesc: String?,
    @SerializedName("menu_pic")
    val menuPic: String?
)