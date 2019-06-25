package yunussimulya.gmail.com.mysample.activity.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import yunussimulya.gmail.com.mysample.R
import yunussimulya.gmail.com.mysample.listener.TmdbApiService
import yunussimulya.gmail.com.mysample.model.Movie
import yunussimulya.gmail.com.mysample.model.MovieResponse
import yunussimulya.gmail.com.mysample.network.TmdbClient

class ReactiveRetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reactive_retrofit)
        val bCall = findViewById<Button>(R.id.bCall)
        bCall.setOnClickListener { call() }
    }

    private fun call() {
        val api = TmdbClient.getClient().create(TmdbApiService::class.java)
        val obj = api.getObservableTopRated(TmdbClient.API_KEY)
        obj.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<MovieResponse> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(value: MovieResponse) {
                        val movies = value.movies
                        for (movie in movies) {
                            Log.e("rx-movie", movie.title)
                        }
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
    }
}
