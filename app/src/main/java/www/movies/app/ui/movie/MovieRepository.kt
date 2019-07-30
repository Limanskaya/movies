package www.movies.app.ui.movie

import www.movies.app.model.MovieResponse
import www.movies.app.net.API
import www.movies.app.base.BaseRepository
import www.movies.app.model.MovieDetail

class MovieRepository(private val api : API) : BaseRepository() {

    suspend fun getUpcomingMovies(): Result<MovieResponse, ErrorMessage> {
        return safeApiCall(
            call = { api.getUpcomingMovies().await() }
        )
    }


    suspend fun getMovieId(movieId: Int): Result<MovieDetail, ErrorMessage> {
        return safeApiCall(
            call = { api.getMovieDetails(movieId).await() }
        )
    }

}