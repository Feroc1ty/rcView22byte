package ru.rykunov.rcview22byte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.rykunov.rcview22byte.databinding.ActivityNewsDetailsBinding

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //close btn
        binding.btnClose.setOnClickListener {
            finish()
        }

        val intent = intent


        setContentView(R.layout.activity_news_details)
    }
}