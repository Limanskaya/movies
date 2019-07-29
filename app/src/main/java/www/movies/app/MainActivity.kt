package www.movies.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import www.movies.app.net.RetrofitService

class MainActivity : AppCompatActivity(), MainRouter {

    private lateinit var movieViewModel: MovieViewModel

    private val repository : MovieRepository = MovieRepository(RetrofitService.api)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        GlobalScope.launch(Dispatchers.Main) {
//            try {
//                val response = repository.getUpcomingMovies()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, MovieFragment.newInstance(), MovieFragment::class.java.name)
                .commitNow()
        }
    }


    override fun addFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragment, Fragment::class.java.name)
            .addToBackStack(Fragment::class.java.name)
            .commit()
    }

}
