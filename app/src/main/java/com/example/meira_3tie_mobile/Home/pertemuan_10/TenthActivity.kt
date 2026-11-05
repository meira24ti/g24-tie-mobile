package com.example.meira_3tie_mobile.Home.pertemuan_10

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.meira_3tie_mobile.databinding.ActivityTenthBinding
import com.google.android.material.chip.Chip

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. TOOLBAR DAN TOMBOL KEMBALI
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Widget Pertemuan 10"
            setDisplayHomeAsUpEnabled(true)
        }

        // 2. TEXTINPUTLAYOUT DAN MATERIALBUTTON
        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text
                ?.toString()
                ?.trim()
                .orEmpty()

            when {
                email.isEmpty() -> {
                    binding.textInputLayout.error =
                        "Email tidak boleh kosong"
                }

                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    binding.textInputLayout.error =
                        "Format email tidak valid"
                }

                else -> {
                    binding.textInputLayout.error = null

                    Toast.makeText(
                        this,
                        "Validasi berhasil: $email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        // 3. CHIPGROUP
        binding.chipGroupFilter
            .setOnCheckedStateChangeListener { group, checkedIds ->

                val selectedChipId = checkedIds.firstOrNull()

                if (selectedChipId != null) {

                    val chip = group.findViewById<Chip>(
                        selectedChipId
                    )

                    Toast.makeText(
                        this,
                        "Filter: ${chip.text}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        // 4. GRIDLAYOUT
        val menuButtons = listOf(
            binding.btnMenu1,
            binding.btnMenu2,
            binding.btnMenu3,
            binding.btnMenu4,
            binding.btnMenu5,
            binding.btnMenu6
        )

        menuButtons.forEachIndexed { index, button ->

            button.setOnClickListener {
                Toast.makeText(
                    this,
                    "Kamu memilih Menu ${index + 1}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
