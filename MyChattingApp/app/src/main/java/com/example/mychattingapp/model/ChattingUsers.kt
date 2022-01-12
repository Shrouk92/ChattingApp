package com.example.mychattingapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users_Table")

data class ChattingUsers(val name:String ="",val email:String="", @PrimaryKey  val uid:String =""){

}

