package www.movies.app.net

import retrofit2.http.GET

interface API {

    @GET("movie/upcoming")
    fun getUpcomingMovies()
}