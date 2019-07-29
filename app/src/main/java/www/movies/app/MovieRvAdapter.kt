package www.movies.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import www.movies.app.model.Movie
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieRvAdapter(
    private val items: List<Movie>,
    private val itemClickListener: (Movie) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {



    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], itemClickListener)
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bind(movie: Movie, itemClickListener: (Movie) -> Unit) {

        itemView.movieRuName.text = movie.title
        itemView.movieEnName.text = movie.originalTitle
        itemView.movieVoteAverage.text = movie.voteAverage.toString()

        itemView.setOnClickListener { itemClickListener(movie) }


    }
}


