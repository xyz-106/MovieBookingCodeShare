package com.jshelf.themoviebookingapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jshelf.themoviebookingapp.data.vos.*
import com.jshelf.themoviebookingapp.persistence.daos.*


@Database(
    entities = [MovieVO::class, ActorVO::class, UserDataVO::class, CardVO::class, MovieCinemaVO::class, TimeSlotVO::class, SnackVO::class, PaymentMethodVO::class, GenreVO::class, DateCinemaAndTimeSlotVO::class],
    version = 22,
    exportSchema = false
)
abstract class MovieBookingDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "THE_MOVIE_BOOKING_DB"

        var dbInstance: MovieBookingDatabase? = null

        fun getDBInstance(context: Context): MovieBookingDatabase? {
            when (dbInstance) {
                null -> {
                    dbInstance =
                        Room.databaseBuilder(context, MovieBookingDatabase::class.java, DB_NAME)
                            .allowMainThreadQueries().fallbackToDestructiveMigration().build()
                }
            }
            return dbInstance
        }
    }

    abstract fun movieDao(): MovieDao
    abstract fun castDao(): CastDao
    abstract fun cinemaAndTimeSlotDao(): CinemaAndTimeSlotDao
    abstract fun paymentDao(): PaymentDao
    abstract fun snackDao(): SnackDao
    abstract fun userDao(): UserDao
}