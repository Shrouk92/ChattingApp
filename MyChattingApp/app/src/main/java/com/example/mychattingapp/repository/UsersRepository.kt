package com.example.mychattingapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mychattingapp.model.ChattingUsers
import com.example.mychattingapp.utils.FirebaseUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.runBlocking

object UsersRepository {

    private val userLiveList=MutableLiveData<ArrayList<ChattingUsers>>()


    // Move this fun to Repo
    fun getAllUsers():MutableLiveData<ArrayList<ChattingUsers>>{

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
                            //  users.add(chattingUsers)
                                val   c= ChattingUsers("empty","empty","empty")
                                users.add(c)
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

      return userLiveList
    }


}