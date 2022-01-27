package com.example.mychattingapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mychattingapp.database.UsersDao
import com.example.mychattingapp.model.ChattingUsers
import com.example.mychattingapp.utils.FirebaseUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.runBlocking

class UsersRepository(private val usersDao: UsersDao) {

    // Receive data from Firebase DB
     var userLiveList=MutableLiveData<ArrayList<ChattingUsers>>()

    //  Get All Users from  Room DB
    val userListFromRDB:MutableLiveData<ArrayList<ChattingUsers>> =usersDao.getUsersFromDB()



    // insert all users to DB which back from firebase
   private suspend fun addUsersToDB(users:MutableLiveData<ArrayList<ChattingUsers>>)
    {
        users.value?.let { usersDao.insertUsers(it) }
    }




    // Get All users from firebase realtime database and save the in th Room DB
    suspend fun getAllUsers():MutableLiveData<ArrayList<ChattingUsers>>{

        val users= ArrayList<ChattingUsers>()


        FirebaseUtils.dbReference.child("user").addValueEventListener(object :
                ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (mysnapshot in snapshot.children)
                    {
                        val chattingUsers=mysnapshot.getValue(ChattingUsers::class.java)
                        if(FirebaseUtils.firebaseAuth.currentUser?.email!=chattingUsers?.email) {
                            if (chattingUsers != null) {
                             users.add(chattingUsers)

                            }

                            else
                            {
                                val   c= ChattingUsers("empty","empty","empty")
                               users.add(c)
                            }
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        userLiveList.postValue(users)
        addUsersToDB(userLiveList)

      return userLiveList
    }


}