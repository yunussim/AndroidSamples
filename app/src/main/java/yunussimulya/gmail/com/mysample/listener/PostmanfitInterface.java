package yunussimulya.gmail.com.mysample.listener;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Yunus on 3/15/2018.
 */

public interface PostmanfitInterface {

    @GET("get")
    Call<String> get();

    @FormUrlEncoded
    @POST("post")
    Call<String> login(@Field("username") String username, @Field("password") String password);
}
