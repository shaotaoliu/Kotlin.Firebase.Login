package com.example.firebaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        btnRegister.setOnClickListener {
            if (etEmail.text.isBlank()) {
                Toast.makeText(this, "Please enter emial.", Toast.LENGTH_SHORT).show()
            }
            else if (etPassword.text.isBlank()) {
                Toast.makeText(this, "Please enter password.", Toast.LENGTH_SHORT).show()
            }
            else if (etConfirmPassword.text.isBlank()) {
                Toast.makeText(this, "Please enter confirm password.", Toast.LENGTH_SHORT).show()
            }
            else if (etPassword.text.toString() != etConfirmPassword.text.toString()) {
                Toast.makeText(this, "Password does not match confirm password.", Toast.LENGTH_SHORT).show()
            }
            else {
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "You are registered successfully.", Toast.LENGTH_SHORT).show()
                        task.result.user?.let { it1 -> Log.i("uid", it1.uid) }

                        val intent = Intent(this, MainActivity::class.java)
                        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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

        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

            //onBackPressed()
        }
    }
}