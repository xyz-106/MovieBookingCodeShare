package com.jshelf.themoviebookingapp

import android.app.Application
import com.jshelf.themoviebookingapp.data.models.MovieBookingModelImpl

class MovieBookingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MovieBookingModelImpl.initDatabase(applicationContext)

    }
}