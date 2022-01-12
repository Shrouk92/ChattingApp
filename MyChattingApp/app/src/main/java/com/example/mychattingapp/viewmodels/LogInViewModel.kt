package com.example.mychattingapp.viewmodels

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.utils.FirebaseUtils.firebaseAuth
import com.example.mychattingapp.ui.MainActivity
import com.example.mychattingapp.ui.SignUpActivity



class LogInViewModel:ViewModel() {


    val email = ObservableField<String>()
    val password = ObservableField<String>()



    // Function to LogIn User
    public fun logInUser(view:View)
    {

        // Sign In to FireBase
        email.get()?.let {
            password.get()?.let { it1 ->
                firebaseAuth
                    .signInWithEmailAndPassword(it, it1)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
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
        }
    }



    // Go to signUp page
    public fun moveToSignUpPage(view: View)
    {
        val intent=Intent(view.context, SignUpActivity::class.java)
        view.context.startActivity(intent)
    }

}