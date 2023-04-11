package com.csci448.sflemington.recallrumble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.csci448.sflemington.recallrumble.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.goToLogIn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        binding.continueAsGuest.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonRegister.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()
            val retypepass = binding.retypePassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && retypepass.isNotEmpty()) {
                if (pass == retypepass) {
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if (it.isSuccessful) {
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Passwords do not match. Please try again.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Please enter all information.", Toast.LENGTH_LONG).show()
            }
        }
    }
}