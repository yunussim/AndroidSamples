package yunussimulya.gmail.com.mysample.network;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Yunus on 3/15/2018.
 */

public class PostmanfitClient {

    private static Retrofit postmanfitClient = null;

    public static Retrofit getClient() {
        if (postmanfitClient == null) {
            postmanfitClient = new Retrofit.Builder()
                    .baseUrl("https://postman-echo.com")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return postmanfitClient;
    }
}
