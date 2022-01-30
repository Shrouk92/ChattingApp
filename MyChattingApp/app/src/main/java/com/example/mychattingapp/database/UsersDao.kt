package com.example.mychattingapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mychattingapp.model.ChattingUsers
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {


   // Get all users from DB to repo
    @Query( "Select * from Users_Table")
     fun getUsers():LiveData<List<ChattingUsers>>


     // Insert Users from back_end "FireBase " to the DB
     // onConflict ignore in order not to repeat data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertUsers(   Users:ArrayList<ChattingUsers>)
}