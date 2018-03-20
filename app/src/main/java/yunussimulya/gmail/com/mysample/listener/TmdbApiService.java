package yunussimulya.gmail.com.mysample.listener;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import yunussimulya.gmail.com.mysample.model.Movie;
import yunussimulya.gmail.com.mysample.model.MovieResponse;

/**
 * Created by Yunus on 3/11/2018.
 */

public interface TmdbApiService {

    /*
    @Path – variable substitution for the API endpoint. For example movie id will be swapped for{id} in the URL endpoint.

    @Query – specifies the query key name with the value of the annotated parameter.

    @Body – payload for the POST call

    @Header – specifies the header with the value of the annotated parameter
     */

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRated(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Movie> getMovieDetail(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<MovieResponse> getObservableTopRated(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Observable<Movie> getObservableMovieDetail(@Path("id") int id, @Query("api_key") String apiKey);
}
