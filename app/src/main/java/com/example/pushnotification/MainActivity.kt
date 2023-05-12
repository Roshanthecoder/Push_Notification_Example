package com.example.pushnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Roshan", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            else{
                val token = task.result

                // Log and toast

                Log.d("roshan", token)
//                Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
            }

            // Get new FCM registration token

        })
    }
}