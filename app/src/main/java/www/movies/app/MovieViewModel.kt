package www.movies.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import www.movies.app.base.BaseRepository
import www.movies.app.model.Movie
import www.movies.app.net.RetrofitService
import kotlin.coroutines.CoroutineContext

class MovieViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : MovieRepository = MovieRepository(RetrofitService.api)

    val loadingLiveData = MutableLiveData<Boolean>()

    val upcomingMoviesLiveData = MutableLiveData<MutableList<Movie>>()

    val errorLiveData = MutableLiveData<String>()



    fun fetchMovies() {
        scope.launch {

            loadingLiveData.postValue(true)

            when(val upcomingMovies = repository.getUpcomingMovies()) {

                is BaseRepository.Result.Success -> {
                    loadingLiveData.postValue(false)
                    upcomingMoviesLiveData.postValue(upcomingMovies.data.results)
                }

                is BaseRepository.Result.Error -> {
                    loadingLiveData.postValue(false)
                    errorLiveData.postValue(upcomingMovies.data.message)
                }
            }

        }
    }


    fun cancelAllRequests() = coroutineContext.cancel()

}