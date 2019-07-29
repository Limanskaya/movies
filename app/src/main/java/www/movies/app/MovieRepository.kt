package www.movies.app

import www.movies.app.model.MovieResponse
import www.movies.app.net.API
import www.movies.app.base.BaseRepository

class MovieRepository(private val api : API) : BaseRepository() {

    suspend fun getUpcomingMovies(): Result<MovieResponse, ErrorMessage> {
        return safeApiCall(
            call = { api.getUpcomingMovies().await() }
        )
    }

}