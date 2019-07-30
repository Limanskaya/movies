package www.movies.app.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.activity_main.*
import www.movies.app.MainActivity
import www.movies.app.adapters.MovieRvAdapter
import www.movies.app.MovieViewModel
import www.movies.app.R
import www.movies.app.model.Movie


class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        setObservers()
        fetchMovies()
    }

    private fun setObservers() {
        movieViewModel.upcomingMoviesLiveData.observe(this, Observer {
            (activity as MainActivity).errorView.visibility = View.GONE

            recyclerView.apply {
                val list: MutableList<Movie> = mutableListOf()
                list.addAll(it)

                adapter = MovieRvAdapter(list) { movie: Movie -> onItemClick(movie) }
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


    private fun fetchMovies() {
        progressBar.visibility = View.VISIBLE
        movieViewModel.fetchMovies()
    }


    private fun onItemClick(movie: Movie) {
        (activity as MainActivity).addFragment(MovieDetailsFragment.newInstance(movie))
    }


    companion object {
        fun newInstance(): MovieFragment = MovieFragment()
    }


}
