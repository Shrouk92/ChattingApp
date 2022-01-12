package com.example.mychattingapp.model

import androidx.room.Entity

@Entity (tableName = "Messages_Table")

data class Message(val message:String="",val senderId:String ="",val RecipientUid:String=""){}
