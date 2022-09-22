package com.jshelf.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jshelf.themoviebookingapp.data.vos.*

@Dao
interface MovieDao {

    //Now Playing Coming Soon
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie(movies: MovieVO?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(genreVO: List<GenreVO>)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieVO>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Int): MovieVO?

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMoviesByType(type: String): List<MovieVO>

    @Query("DELETE FROM movies")
    fun deleteAllMovies()
    }