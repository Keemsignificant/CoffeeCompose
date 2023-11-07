package com.chirayut.coffecompose.service

import com.google.gson.annotations.SerializedName

data class CoffeeResponse (
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("menu_name")
    val menu_name: String? = null,

    @field:SerializedName("menu_desc")
    val menu_desc: String? = null,

    @field:SerializedName("menu_pic")
    val menu_pic: String? = null,
)