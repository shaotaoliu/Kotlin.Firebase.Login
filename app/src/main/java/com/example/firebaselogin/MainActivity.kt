package com.example.firebaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = intent.getStringExtra("email")
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        tvEmail.text = email

        val btnSignOut = findViewById<Button>(R.id.btnSignOut)
        btnSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

/*
 How to get SHA1:
 1. Click "Gradle" on the top-right corner.
 2. Click the elephant icon.
 3. Enter "gradle signingReport" and run
*/