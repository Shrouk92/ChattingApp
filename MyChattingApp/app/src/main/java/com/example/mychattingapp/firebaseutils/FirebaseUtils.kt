package com.example.mychattingapp.firebaseutils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object FirebaseUtils {
    var firebaseAuth: FirebaseAuth = Firebase.auth

}