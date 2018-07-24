package com.involves.involvesteste.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
        @PrimaryKey
        val id : Int,
        @SerializedName("poster_path") val posterPath : String?,
        @SerializedName("overview") val overview : String?,
        @SerializedName("release_date") val releaseDate : String?,
        @SerializedName("original_title") val originalTitle : String?,
        @SerializedName("title") val title : String?,
        @SerializedName("backdrop_path") val backdropPath : String?,
        @SerializedName("genre_ids") val genreIds: List<Int>?

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            arrayListOf<Int>().apply {
                parcel.readList(this, Int::class.java.classLoader)
            }
            ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(posterPath)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeString(originalTitle)
        parcel.writeString(title)
        parcel.writeString(backdropPath)
        parcel.writeList(genreIds)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}