package com.example.meira_3tie_mobile.Message.tutorial
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.meira_3tie_mobile.databinding.ActivityTutorialMessageBinding

class TutorialMessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTutorialMessageBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Tutorial Message"
            setDisplayHomeAsUpEnabled(true)
        }

        val adapter = TutorialFragmentAdapter(this)

        binding.tutorialMessageViewPager.adapter = adapter

        binding.dotIndicator.attachTo(
            binding.tutorialMessageViewPager
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
