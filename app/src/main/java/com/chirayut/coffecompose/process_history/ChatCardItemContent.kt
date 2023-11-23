package com.chirayut.coffecompose.process_history

data class ChatCardItemContent(
    val id:Int = 0,
    val image : Int,
    val messengerName:String,
    val message:String,
    val messageDate:String,
    val messageNumberBadge:Int = 0
)