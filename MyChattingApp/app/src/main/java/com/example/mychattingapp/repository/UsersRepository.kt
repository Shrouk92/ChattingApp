package com.example.mychattingapp.repository

import com.example.mychattingapp.model.ChattingUsers
import com.example.mychattingapp.utils.FirebaseUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.runBlocking

object UsersRepository {


    // Move this fun to Repo
    fun getAllUsers():ArrayList<ChattingUsers>
    {
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

      return users
    }


}