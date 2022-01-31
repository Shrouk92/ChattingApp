package com.example.mychattingapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.mychattingapp.database.UsersDao
import com.example.mychattingapp.database.UsersDataBase
import com.example.mychattingapp.model.ChattingUsers
import com.example.mychattingapp.utils.FirebaseUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*

class UsersRepository(private val usersDataBase: UsersDataBase) {

    // Receive data from Firebase
   private val usersList = MutableLiveData<ArrayList<ChattingUsers>>()


    // Live data from DB " offline caching "
    val usersLiveList: LiveData<List<ChattingUsers>> =
        usersDataBase.getDao.getUsers()


    // saving data back from network to DB with calling result from network
    suspend fun refreshDatabase() {
        withContext(Dispatchers.IO)
        {
            // userList have the users from RealTime Firebase database
            val usersList = getAllUsers()
                delay(1500)
            usersList.value?.let {
                // saving users in the database
                usersDataBase.getDao.insertUsers(it)
            }

        }
    }


    // Get All users from firebase realtime database and save the in th Room DB
    suspend  fun getAllUsers(): MutableLiveData<ArrayList<ChattingUsers>> {

        val users = ArrayList<ChattingUsers>()
        FirebaseUtils.dbReference.child("user").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (mysnapshot in snapshot.children) {
                    val chattingUsers = mysnapshot.getValue(ChattingUsers::class.java)
                    if (FirebaseUtils.firebaseAuth.currentUser?.email != chattingUsers?.email) {
                        if (chattingUsers != null) {
                            users.add(chattingUsers)

                        } else {
                            val c = ChattingUsers("empty", "empty", "empty")
                            users.add(c)
                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        usersList.postValue(users)
        return usersList
    }


}