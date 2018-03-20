package vivenkko.weather.model.retrofit;


import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import vivenkko.weather.model.forecast.ForecastInfo;
import vivenkko.weather.model.weather.WeatherInfo;

public interface ApiOpenWeather {

    @Multipart
    @POST("api/v1/auth/register")
    Call<User> register(@Part("email") RequestBody email,
                        @Part("password") RequestBody password,
                        @Part("displayName") RequestBody displayName,
                        @Part MultipartBody.Part file);

    @POST("api/v1/auth/login")
    Call<User> login(@Body User user);

    @GET("/data/2.5/weather")
    Call<WeatherInfo> getWeatherInfoByLatLng(@Query("lat") Double lat, @Query("lon") Double lon);

    @GET("/data/2.5/forecast")
    Call<List<ForecastInfo>> getForecastInfoByLatLng(@Query("lat") Double lat, @Query("lon") Double lon);

}
