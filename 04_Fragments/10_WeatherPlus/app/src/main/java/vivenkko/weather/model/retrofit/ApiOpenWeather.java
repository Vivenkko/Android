package vivenkko.weather.model.retrofit;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vivenkko.weather.model.weather.WeatherInfo;

public interface ApiOpenWeather {

    @FormUrlEncoded
    @POST("/keeper/api/auth/register")
    Call<User> register(@Field("nombre") String nombre, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/keeper/api/auth/login")
    Call<User> login(@Field("email") String email, @Field("password") String password);

    @GET("/data/2.5/weather")
    Call<WeatherInfo> getWeatherInfoByLatLng(@Query("lat") Double lat, @Query("lon") Double lon);

    @GET("/data/2.5/forecast")
    Call<WeatherInfo> getForecastInfoByCity(@Query("q") String city);

}
