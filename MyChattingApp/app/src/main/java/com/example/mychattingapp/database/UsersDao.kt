package com.example.mychattingapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mychattingapp.model.ChattingUsers

@Dao
interface UsersDao {


   // Get all users from DB and send to ViewModel
    @Query( "Select * from Users_Table")
     fun getAllUsers(): LiveData<ArrayList<ChattingUsers>>


     // Insert Users from back_end "FireBase " to the DB
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers( vararg Users:ChattingUsers)
}