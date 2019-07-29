package www.movies.app.net

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import www.movies.app.model.MovieResponse

interface API {

    @GET("movie/upcoming")
    fun getUpcomingMovies() : Deferred<Response<MovieResponse>>
}