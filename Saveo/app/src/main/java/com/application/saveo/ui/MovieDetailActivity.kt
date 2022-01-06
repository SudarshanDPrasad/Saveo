package com.application.saveo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.application.saveo.R
import com.application.saveo.databinding.ActivityMovieDetailBinding
import com.application.saveo.response.ResultDTO
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieDetailBinding: ActivityMovieDetailBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieDetailBinding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(movieDetailBinding.root)

        val data = intent.getSerializableExtra("details") as ResultDTO
        movieDetailBinding.dataDetails = data

        Glide.with(movieDetailBinding.ivDetailImage)
            .load("https://image.tmdb.org/t/p/w500/" + data.posterPath)
            .into(movieDetailBinding.ivDetailImage)

        movieDetailBinding.btnBacktoMain.setOnClickListener {
            val intent : Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val vote : Int = (data.voteAverage/2).toInt()
        movieDetailBinding.vvt.setText(vote.toString())


        if(data.voteAverage.toString() <=2.0.toString()) {
            movieDetailBinding.star1.setBackgroundResource(R.drawable.blackstar)
        }

        if(data.voteAverage.toString() <= 4.0.toString()){
            movieDetailBinding.star2.setBackgroundResource(R.drawable.blackstar)
        }

        if(data.voteAverage.toString() <= 6.0.toString()){
            movieDetailBinding.star3.setBackgroundResource(R.drawable.blackstar)
        }

        if(data.voteAverage.toString() <= 8.0.toString()){
            movieDetailBinding.star4.setBackgroundResource(R.drawable.blackstar)

        }

        if(data.voteAverage.toString() <= 9.0.toString()){
            movieDetailBinding.star5.setBackgroundResource(R.drawable.blackstar)
        }
    }
}