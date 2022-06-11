package com.example.exam

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Main : AppCompatActivity() {
    companion object {
        const val LINK = "sign"
    }

    fun ImageBySign (sign: String) {
        val image = findViewById<ImageView>(R.id.image)
        if (sign == "aries") image.setImageResource(R.drawable.aries)
        if (sign == "taurus") image.setImageResource(R.drawable.taurus)
        if (sign == "gemini") image.setImageResource(R.drawable.gemini)
        if (sign == "cancer") image.setImageResource(R.drawable.cancer)
        if (sign == "leo") image.setImageResource(R.drawable.leo)
        if (sign == "virgo") image.setImageResource(R.drawable.virgo)
        if (sign == "libra") image.setImageResource(R.drawable.libra)
        if (sign == "scorpio") image.setImageResource(R.drawable.scorpio)
        if (sign == "sagittarius") image.setImageResource(R.drawable.sagittarius)
        if (sign == "capricorn") image.setImageResource(R.drawable.capricorn)
        if (sign == "aquarius") image.setImageResource(R.drawable.aquarius)
        if (sign == "pisces") image.setImageResource(R.drawable.pisces)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onStart() {
        super.onStart()


        val sign = intent.getStringExtra(LINK).toString()
        val date1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val date = findViewById<TextView>(R.id.date)
        val back = findViewById<TextView>(R.id.back)
        val horoscope = findViewById<Button>(R.id.horoscope)
        val fact = findViewById<Button>(R.id.fact)
        val title = findViewById<TextView>(R.id.textTitle)

        title.setText(sign.replaceFirstChar{it.titlecase()})
        date.setText(date1)
        ImageBySign(sign)

        back.setOnClickListener {
            this.finish()
        }

        horoscope.setOnClickListener {
            val intent1 = Intent(this, Horoscope::class.java)
            intent1.putExtra(Main.LINK, sign)
            startActivity(intent1)
        }

        fact.setOnClickListener {
            val intent2 = Intent(this, Fact::class.java)
            startActivity(intent2)
        }
    }
}