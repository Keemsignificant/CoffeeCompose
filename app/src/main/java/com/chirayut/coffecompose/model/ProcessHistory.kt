package com.chirayut.coffecompose.model

import com.google.gson.annotations.SerializedName

data class ProcessHistory (
    @SerializedName("id_process")
    val idProcess: String?,
    @SerializedName("id_munu")
    val idMenu: String?,
    @SerializedName("menu_desc")
    val menuDesc: String?,
    @SerializedName("menu_pic")
    val menuPic: String?,
    @SerializedName("process_title")
    val processTitle: String?,
    @SerializedName("process_time")
    val processTime: String?,
    @SerializedName("process_description")
    val processDescription: String?,
    @SerializedName("process_pic")
    val processPic: String?
)