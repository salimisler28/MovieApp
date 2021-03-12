package com.teknasyon.movieapp.app.util

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.progressindicator.CircularProgressIndicator

fun String?.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url: String?) {
    Glide
        .with(context)
        .load("https://www.themoviedb.org/t/p/w1280/$url")
        .into(this)
}