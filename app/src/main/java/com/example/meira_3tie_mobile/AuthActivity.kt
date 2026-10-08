package com.example.meira_3tie_mobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.meira_3tie_mobile.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref =
            getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {

            val username =
                binding.edtUsername.text.toString()

            val password =
                binding.edtPassword.text.toString()

            if (username == password) {

                val editor = sharedPref.edit()

                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                val intent =
                    Intent(this, MainActivity::class.java)

                startActivity(intent)

            } else {

                AlertDialog.Builder(this)
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}