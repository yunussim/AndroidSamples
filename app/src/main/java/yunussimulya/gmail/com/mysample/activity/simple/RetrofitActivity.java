package yunussimulya.gmail.com.mysample.activity.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yunussimulya.gmail.com.mysample.R;
import yunussimulya.gmail.com.mysample.network.TmdbClient;
import yunussimulya.gmail.com.mysample.listener.RetrofitInterface;
import yunussimulya.gmail.com.mysample.model.Movie;
import yunussimulya.gmail.com.mysample.model.MovieResponse;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        Button bCall = findViewById(R.id.bCall);
        bCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });
    }

    private void call() {
        RetrofitInterface apiService = TmdbClient.getClient().create(RetrofitInterface.class);
        Call<MovieResponse> topRated = apiService.getTopRated(TmdbClient.API_KEY);
        topRated.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movies = response.body();
                int i = 0;
                for (Movie movie : movies.getMovies()) {
                    Log.e("id", movie.getId() + "");
                    Log.e("title", movie.getTitle());
                    for (int genre : movie.getGenreIds()) {
                        Log.e("genre", genre + "");
                    }
                    if (i == 0) {
                        Call<Movie> detail = apiService.getMovieDetail(movie.getId(), TmdbClient.API_KEY);
                        detail.enqueue(new Callback<Movie>() {
                            @Override
                            public void onResponse(Call<Movie> call, Response<Movie> response) {
                                Log.e("detail-id", response.body().getId() + "");
                                Log.e("detail-tit", response.body().getTitle());
                            }

                            @Override
                            public void onFailure(Call<Movie> call, Throwable t) {

                            }
                        });
                    }
                    i++;
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }
}
