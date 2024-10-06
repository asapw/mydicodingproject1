package com.example.mydicodingproject

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataRapper = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Rapper>("key_rapper", Rapper::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Rapper>("key_rapper")
        }

        if (dataRapper != null) {
            val imgPhoto: ImageView = findViewById(R.id.iv_detail_photo)
            val tvName: TextView = findViewById(R.id.tv_detail_name)
            val tvDescription = findViewById<TextView>(R.id.tv_detail_description)
            val tvAdditionalInfo = findViewById<TextView>(R.id.tv_detail_additional_info)

            imgPhoto.setImageResource(dataRapper.photo)
            tvName.text = dataRapper.name
            tvDescription.text = dataRapper.description

            tvAdditionalInfo.text = "Albums: ${dataRapper.albums}\nAwards: ${dataRapper.awards}"

        }

        // Handle the share button click
        val shareButton: ImageButton = findViewById(R.id.btn_share)
        shareButton.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Check out this rapper: ${dataRapper?.name}")
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }

}