package yunussimulya.gmail.com.mysample.activity.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yunussimulya.gmail.com.mysample.R
import yunussimulya.gmail.com.mysample.network.TmdbClient
import yunussimulya.gmail.com.mysample.listener.TmdbApiService
import yunussimulya.gmail.com.mysample.model.Movie
import yunussimulya.gmail.com.mysample.model.MovieResponse

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        val bCall = findViewById<Button>(R.id.bCall)
        bCall.setOnClickListener { call() }
    }

    private fun call() {
        val apiService = TmdbClient.getClient().create(TmdbApiService::class.java)
        val topRated = apiService.getTopRated(TmdbClient.API_KEY)
        topRated.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies = response.body()
                var i = 0
                for (movie in movies!!.movies) {
                    Log.e("id", movie.id.toString() + "")
                    Log.e("title", movie.title)
                    for (genre in movie.genreIds) {
                        Log.e("genre", genre.toString() + "")
                    }
                    if (i == 0) {
                        val detail = apiService.getMovieDetail(movie.id, TmdbClient.API_KEY)
                        detail.enqueue(object : Callback<Movie> {
                            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                                Log.e("detail-id", response.body()!!.id.toString() + "")
                                Log.e("detail-tit", response.body()!!.title)
                            }

                            override fun onFailure(call: Call<Movie>, t: Throwable) {

                            }
                        })
                    }
                    i++
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }
        })
    }
}
