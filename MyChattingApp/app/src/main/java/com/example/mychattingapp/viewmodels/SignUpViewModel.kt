package com.example.mychattingapp.viewmodels

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.utils.FirebaseUtils.dbReference
import com.example.mychattingapp.utils.FirebaseUtils.firebaseAuth
import com.example.mychattingapp.model.ChattingUsers
import com.example.mychattingapp.ui.MainActivity

class SignUpViewModel:ViewModel() {


    val name = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()


    // SignUp
    public fun signUp(view: View) {

        firebaseAuth
            .createUserWithEmailAndPassword(email.get().toString(), password.get().toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    // Save User In FireBase database
                    saveUserToFirebaseRealTimeDataBase(name.get().toString(),email.get().toString(),
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


    private fun saveUserToFirebaseRealTimeDataBase(  name:String, email:String,UId:String )
    {
        dbReference.child("user").child(UId).setValue(ChattingUsers(name,email, UId))


    }
}