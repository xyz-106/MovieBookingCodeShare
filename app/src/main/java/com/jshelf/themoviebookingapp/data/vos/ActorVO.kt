package com.jshelf.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.jshelf.themoviebookingapp.persistence.typeconverters.MovieListTypeConverter

@Entity(tableName = "actors")
@TypeConverters(
    MovieListTypeConverter::class
)
data class ActorVO(

    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    val adult: Boolean?,

    @SerializedName("gender")
    @ColumnInfo(name = "gender")
    val gender: Int?,

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int=0,

    @SerializedName("known_for")
    @ColumnInfo(name = "known_for")
    val knownFor: List<MovieVO>?,

    @SerializedName("known_for_department")
    @ColumnInfo(name = "known_for_department")
    val knownForDepartment: String?,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @SerializedName("popularity")
     @ColumnInfo(name = "popularity")
    val popularity: Double?,

    @SerializedName("profile_path")
      @ColumnInfo(name = "profile_path")
    val profilePath: String?,


    @SerializedName("original_name")
    @ColumnInfo(name = "original_name")
    val orginalName: String?,

    @SerializedName("cast_id")
    @ColumnInfo(name = "cast_id")
    val castId: Int?,

    @SerializedName("character")
    @ColumnInfo(name = "character")
    val character: String?,

    @SerializedName("credit_id")
    @ColumnInfo(name = "credit_id")
    val creditId: String?,

    @SerializedName("order")
    @ColumnInfo(name = "order")
    val order: Int?,
)