package ru.rykunov.rcview22byte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
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
        val title = intent.getStringExtra(MainActivity.NEWS_TITLE)
        val details = intent.getStringExtra(MainActivity.NEWS_DETAILS)
        val url = intent.getStringExtra(MainActivity.NEWS_URL)
        val img = intent.getStringExtra(MainActivity.NEWS_IMG)
        val date = intent.getStringExtra(MainActivity.NEWS_DATE)

        with(binding){
            tvTitle.setText(title)
            tvDescription.setText(details)
            tvDate.setText(date)
            Glide.with(applicationContext)
                .load(img)
                .into(binding.imAvatar)
        }
    }
}