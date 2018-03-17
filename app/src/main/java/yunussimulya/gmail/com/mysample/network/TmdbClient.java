package yunussimulya.gmail.com.mysample.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yunus on 3/11/2018.
 */

public class TmdbClient {

    public static final String API_KEY = "108faf9494718ba593d60bbec84d992c";

    public static final String BASE_URL = "http://api.themoviedb.org/3/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
