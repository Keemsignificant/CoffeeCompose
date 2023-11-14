package com.chirayut.coffecompose.model

import com.google.gson.annotations.SerializedName

data class CoffeeDTO(
    @SerializedName("id")
    val menuId: String? = null,
    @SerializedName("menu_name")
    val menuName: String? = null,
    @SerializedName("menu_desc")
    val menuDesc: String? = null,
    @SerializedName("menu_pic")
    val menuPic: String? = null,
    @SerializedName("menu_detail_pic_list")
    val menuDetailPicList: List<String?> = listOf()
)