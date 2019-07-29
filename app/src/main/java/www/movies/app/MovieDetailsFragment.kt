package www.movies.app


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import www.movies.app.model.Movie


class MovieDetailsFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private var movieId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieId = arguments?.getInt(MOVIE_ID)
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setObservers()
        fetchMovieId()
    }

//    private fun setObservers() {
//        movieViewModel.movieDetailLiveData.observe(this, Observer {
//
//            }
//        })
//    }


    private fun fetchMovieId() {
        movieId?.let { movieViewModel.fetchMovieId(it) }
    }


    companion object {
        const val MOVIE_ID = "movie_id"

        fun newInstance(movie: Movie): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val bundle = Bundle().apply {
                putInt(MOVIE_ID, movie.id)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}
