package com.example.mychattingapp.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

object FirebaseUtils {
    var firebaseAuth: FirebaseAuth = Firebase.auth
    var dbReference:DatabaseReference= FirebaseDatabase.getInstance().reference




}