package com.example.mychattingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mychattingapp.model.ChattingUsers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database (entities = [ChattingUsers::class], version = 1, exportSchema = false)
abstract  class UsersDataBase : RoomDatabase() {

     abstract fun getDao():UsersDao
     companion object
     {
         @Volatile
         private lateinit var INSTANCE : UsersDataBase


         @InternalCoroutinesApi
         fun getDatabase(context: Context):UsersDataBase
         {
             val tempInstance= INSTANCE
             if(tempInstance!= null)
             {
                 return tempInstance
             }
             synchronized(this)
             {

                     val ins=Room.databaseBuilder(context,UsersDataBase::class.java,
                         "MyChatDatabse")
                         .build()
                 INSTANCE=ins
                 return INSTANCE

             }

         }
     }



}