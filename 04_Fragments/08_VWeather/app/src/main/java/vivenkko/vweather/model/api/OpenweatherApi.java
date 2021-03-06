package vivenkko.vweather.model.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vivenkko.vweather.model.User;
import vivenkko.vweather.model.forecast.ForecastInfo;
import vivenkko.vweather.model.weather.WeatherInfo;

/**
 * Created by lmlopez on 13/02/2018.
 */

public interface OpenweatherApi {

    //@GET("/data/2.5/weather?APPID=3bcfcde9b7438aa7696f020ed75f5673&units=metric&lang=es")
    @GET("/data/2.5/weather")
    Call<WeatherInfo> getWeatherInfoByCity(@Query("q") String city);

    @GET("/data/2.5/forecast")
    Call<ForecastInfo> getForecastInfoByCity(@Query("q") String city);

    @FormUrlEncoded
    @POST("https://home.openweathermap.org/users/sign_in")
    Call<User> login(@Field("email") String email, @Field("password")String password);

}
