package com.example.firebaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", currentUser.email)
            startActivity(intent)
        }

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        btnLogin.setOnClickListener {
            if (etEmail.text.isBlank()) {
                Toast.makeText(this, "Please enter emial.", Toast.LENGTH_SHORT).show()
            }
            else if (etPassword.text.isBlank()) {
                Toast.makeText(this, "Please enter password.", Toast.LENGTH_SHORT).show()
            }
            else {
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("email", email)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}