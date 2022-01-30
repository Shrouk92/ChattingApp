package com.example.mychattingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mychattingapp.model.ChattingUsers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [ChattingUsers::class], version = 1, exportSchema = false)
abstract class UsersDataBase : RoomDatabase() {

    abstract val getDao: UsersDao

}
private lateinit var INSTANCE: UsersDataBase

@InternalCoroutinesApi
fun getDatabase(context: Context):UsersDataBase
{
    synchronized(UsersDataBase::class.java)
    {
        if(!::INSTANCE.isInitialized)
        {
            INSTANCE=Room.databaseBuilder(context,UsersDataBase::class.java,"Users_database")
                .build()
        }
    }
    return INSTANCE
}



