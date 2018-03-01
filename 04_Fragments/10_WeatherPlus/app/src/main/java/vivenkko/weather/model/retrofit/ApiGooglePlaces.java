package vivenkko.weather.model.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vivenkko.weather.model.details.DetailsResult;
import vivenkko.weather.model.prediction.PredictionResult;

public interface ApiGooglePlaces {

    @GET("maps/api/place/autocomplete/json?type=(cities)&language=es")
    Call<PredictionResult> autoComplete(@Query("input") String text);

    @GET("maps/api/place/details/json")
    Call<DetailsResult> getPlaceDetails(@Query("place_id") String text);


}
