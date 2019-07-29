package www.movies.app.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    val id: Int,
    @Json(name = "vote_average")
    val voteAverage: Double,
    val title: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    val overview: String?,
    @Json(name = "original_title")
    val originalTitle: String?,
    @Json(name = "release_date")
    val releaseDate: String?

)