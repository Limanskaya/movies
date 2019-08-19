package www.movies.app.ui.moviedetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_movie_details.progressBar
import www.movies.app.ui.MainActivity
import www.movies.app.adapters.MovieGenreRvAdapter
import www.movies.app.ui.movie.MovieViewModel
import www.movies.app.R
import www.movies.app.model.Genre
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

        genreRv?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        setObservers()
        fetchMovieId()
    }

    private fun setObservers() {
        movieViewModel.movieDetailLiveData.observe(this, Observer {
            (activity as MainActivity).errorView.visibility = View.GONE

            movieName.text = it.title
            movieOverview.text = it.overview


            if (it.haveRating) {
                movieVoteAverage.text = it.voteAverage.toString()
                movieVoteAverage.visibility = View.VISIBLE
            } else movieVoteAverage.visibility = View.INVISIBLE

            if (movieOverview.text.isNotEmpty()) {
                movieOverview.visibility = View.VISIBLE
            } else movieOverview.visibility = View.INVISIBLE

            Picasso.get()
                    .load(IMAGE_PATH_W500 + it.posterPath)
                    .placeholder(R.drawable.image_placeholder)
                    .error(R.drawable.image_placeholder)
                    .into(movieImage)

            genreRv.apply {
                val list: MutableList<Genre> = mutableListOf()
                it.genres?.let { it1 ->
                    if (it1.size > 3) list.addAll(it1.subList(0, 3)) else list.addAll(it1)
                }

                adapter = MovieGenreRvAdapter(list)
                adapter?.notifyDataSetChanged()
            }
        })

        movieViewModel.errorLiveData.observe(this, Observer {
            progressBar.visibility = View.GONE
            (activity as MainActivity).errorView.visibility = View.VISIBLE
            (activity as MainActivity).errorLabel.text = movieViewModel.errorLiveData.value
        })

        movieViewModel.loadingLiveData.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }


    private fun fetchMovieId() {
        progressBar.visibility = View.VISIBLE
        movieId?.let { movieViewModel.fetchMovieDetail(it) }
    }


    override fun onStop() {
        (activity as MainActivity).errorView.visibility = View.GONE

        movieViewModel.cancelJob()
        super.onStop()
    }


    companion object {
        const val MOVIE_ID = "movie_id"
        const val IMAGE_PATH_W500 = "https://image.tmdb.org/t/p/w500"


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
