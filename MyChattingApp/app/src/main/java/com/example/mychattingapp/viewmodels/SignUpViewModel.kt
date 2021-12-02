package com.example.mychattingapp.viewmodels

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.firebaseutils.FirebaseUtils
import com.example.mychattingapp.firebaseutils.FirebaseUtils.dbReference
import com.example.mychattingapp.firebaseutils.FirebaseUtils.firebaseAuth
import com.example.mychattingapp.model.ChattingUsers
import com.example.mychattingapp.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpViewModel:ViewModel() {


    val name = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()


    // SignUp
    public fun Sign_Up(view: View) {

        firebaseAuth
            .createUserWithEmailAndPassword(email.get().toString(), password.get().toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    // Save User In database
                    Save_User_InDB(name.get().toString(),email.get().toString(),
                        firebaseAuth.currentUser?.uid.toString()
                    )
                    val intent = Intent(view.context, MainActivity::class.java)
                    view.context.startActivity(intent)
                    val activity=view.context as Activity
                    activity.finish()



                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        view.context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


    }


    private fun Save_User_InDB(  name:String, email:String,UId:String )
    {
        dbReference.child("user").child(UId).setValue(ChattingUsers(name,email, UId))


    }
}