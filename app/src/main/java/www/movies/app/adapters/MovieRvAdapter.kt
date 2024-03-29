package www.movies.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import www.movies.app.model.Movie
import kotlinx.android.synthetic.main.movie_list_item.view.*
import www.movies.app.R

class MovieRvAdapter(
    private val items: List<Movie>,
    private val itemClickListener: (Movie) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).bind(items[position], itemClickListener)
    }

}

class MovieViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bind(movie: Movie, itemClickListener: (Movie) -> Unit) {

        Picasso.get()
            .load(IMAGE_PATH_W185 + movie.posterPath)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .into(itemView.movieImage)

        itemView.movieRuName.text = movie.title
        itemView.movieEnName.text = movie.originalTitle
        itemView.movieDate.text = movie.date

        if (movie.haveRating) {
            itemView.movieVoteAverage.text = movie.voteAverage.toString()
        } else itemView.movieVoteAverage.visibility = View.INVISIBLE

        itemView.setOnClickListener { itemClickListener(movie) }

    }

    companion object {
        const val IMAGE_PATH_W185 = "https://image.tmdb.org/t/p/w185/"
    }
}


