package com.example.mychattingapp.viewmodels

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.firebaseutils.FirebaseUtils.firebaseAuth
import com.example.mychattingapp.ui.MainActivity
import com.example.mychattingapp.ui.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LogInViewModel:ViewModel() {
    private lateinit var auth: FirebaseAuth


    val email =ObservableField<String>()
    val password=ObservableField<String>()



    // Function to LogIn User
    public fun Log_In_User(view:View)
    {
        val mail=email.get().toString()
        val pass=password.get().toString()
        auth = Firebase.auth
        // Sign up to FireBase
        firebaseAuth
            .signInWithEmailAndPassword(mail,pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(view.context, MainActivity::class.java)
                    view.context.startActivity(intent)


                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        view.context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }



    // Go to signUp page
    public fun Move_To_SignUpPage(view: View)
    {
        val intent=Intent(view.context, SignUpActivity::class.java)
        view.context.startActivity(intent)
    }

}