package www.movies.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_genre_list_item.view.*
import www.movies.app.R
import www.movies.app.model.Genre

class MovieGenreRvAdapter(
    private val items: List<Genre>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GenreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_genre_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GenreViewHolder).bind(items[position])
    }

}


class GenreViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bind(genre: Genre) {
        itemView.genreName.text = genre.name
    }
}