package vivenkko.vweather.model.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vivenkko.vweather.model.details.DetailsResult;
import vivenkko.vweather.model.prediction.PredictionResult;

/**
 * Created by lmlopez on 19/02/2018.
 */

public interface GooglePlacesApi {

    @GET("maps/api/place/autocomplete/json?type=(cities)&language=es")
    Call<PredictionResult> autoComplete(@Query("input") String text);

    @GET("maps/api/place/details/json")
    Call<DetailsResult> getPlaceDetails(@Query("place_id") String text);


}
