package com.example.mychattingapp.viewmodels

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.firebaseutils.FirebaseUtils
import com.example.mychattingapp.firebaseutils.FirebaseUtils.firebaseAuth
import com.example.mychattingapp.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
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
}