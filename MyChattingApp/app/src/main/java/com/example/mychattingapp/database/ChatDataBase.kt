package com.example.mychattingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mychattingapp.model.ChattingUsers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database (entities = [ChattingUsers::class], version = 1, exportSchema = false)
abstract  class ChatDataBase : RoomDatabase() {

     abstract fun getDao():UsersDao
     private lateinit var INSTANCE : ChatDataBase

     @InternalCoroutinesApi
     fun getDatabase(context: Context):ChatDataBase
     {
         synchronized(ChatDataBase::class.java)
         {
             if (!::INSTANCE.isInitialized)
             {
                 INSTANCE=Room.databaseBuilder(context,ChatDataBase::class.java,"Chat_Databse")
                     .build()
             }
         }
         return INSTANCE
     }

}