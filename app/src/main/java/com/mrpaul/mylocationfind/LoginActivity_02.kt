package com.mrpaul.mylocationfind

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mrpaul.mylocationfind.ViewModel.AuthenticationViewModel
import com.mrpaul.mylocationfind.databinding.ActivityLogin02Binding

class LoginActivity_02 : AppCompatActivity() {
    private lateinit var binding: ActivityLogin02Binding
    private lateinit var authenticationViewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLogin02Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        authenticationViewModel = ViewModelProvider(this).get(AuthenticationViewModel::class.java)

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "Please enter valid password", Toast.LENGTH_SHORT).show()
            } else {
                authenticationViewModel.login(email, password, {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }, {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                })
            }
        }

        binding.registerTxt.setOnClickListener {
            startActivity(Intent(this, RegisterActivity_02::class.java))
        }

        binding.forgotPass.setOnClickListener {
            Toast.makeText(this, "Forgot password", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser != null) {
            startActivity(Intent(this@LoginActivity_02, MainActivity::class.java))
            finish()
        }
    }
}