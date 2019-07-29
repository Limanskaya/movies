package www.movies.app.net

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import www.movies.app.model.MovieDetail
import www.movies.app.model.MovieResponse

interface API {

    @GET("movie/upcoming")
    fun getUpcomingMovies() : Deferred<Response<MovieResponse>>

    @GET("movie/{movie_id}")
    fun getMovieId(@Path("movie_id") movieId: Int) : Deferred<Response<MovieDetail>>
}