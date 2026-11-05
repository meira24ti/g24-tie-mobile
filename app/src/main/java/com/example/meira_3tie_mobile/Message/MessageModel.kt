package com.example.meira_3tie_mobile.Message

import com.bumptech.glide.load.resource.bitmap.CircleCrop

data class MessageModel(
    val senderName: String,
    val messageText: String,
    val avatarUrl: String,
    val time: String
)