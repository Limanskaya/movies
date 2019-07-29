package www.movies.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, MovieFragment.newInstance(), MovieFragment::class.java.name)
                .commitNow()
        }
    }


    fun addFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragment, Fragment::class.java.name)
            .addToBackStack(Fragment::class.java.name)
            .commit()
    }

}
