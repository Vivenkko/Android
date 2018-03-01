package vivenkko.weather;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.weather.model.retrofit.ApiGooglePlaces;
import vivenkko.weather.model.retrofit.ApiOpenWeather;
import vivenkko.weather.model.retrofit.GooglePlacesResultAdapter;
import vivenkko.weather.model.details.DetailsResult;
import vivenkko.weather.model.prediction.Prediction;
import vivenkko.weather.model.retrofit.services.ServiceGeneratorGoogle;
import vivenkko.weather.model.retrofit.services.ServiceGeneratorWeather;
import vivenkko.weather.model.weather.WeatherInfo;

public class WeatherFragment extends Fragment {
    DelayAutoCompleteTextView autoComplete;
    TextView cityName, temperature;

    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        autoComplete = view.findViewById(R.id.autoCompleteTextViewCity);

        autoComplete = view.findViewById(R.id.autoCompleteTextViewCity);
        autoComplete.setAdapter(new GooglePlacesResultAdapter(getActivity()));

        autoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prediction p = (Prediction) autoComplete.getAdapter().getItem(position);

                // Aquí se ejecuta lo necesario para realizar la petición que obtiene los
                // detalles del sitio, en particular, la latitud y longitud
                ApiGooglePlaces apiGooglePlaces = ServiceGeneratorGoogle.createService(ApiGooglePlaces.class);
                Call<DetailsResult> call = apiGooglePlaces.getPlaceDetails(p.getPlace_id());

                call.enqueue(new Callback<DetailsResult>() {
                    @Override
                    public void onResponse(Call<DetailsResult> call, Response<DetailsResult> response) {
                        if (response.isSuccessful()) {
                            final DetailsResult result = response.body();

                            ApiOpenWeather apiOpenWeather = ServiceGeneratorWeather.createService(ApiOpenWeather.class);
                            Call<WeatherInfo> call2 = apiOpenWeather.getWeatherInfoByLatLng(
                                    result.getResult().getGeometry().getLocation().lat,
                                    result.getResult().getGeometry().getLocation().lng
                            );

                            call2.enqueue(new Callback<WeatherInfo>() {
                                @Override
                                public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                                    if (response.isSuccessful()){
                                        WeatherInfo resultWeather = response.body();

                                        cityName.setText(resultWeather.getName());
                                        cityName.setText(resultWeather.getMain().getTemp().toString());

                                    }
                                }

                                @Override
                                public void onFailure(Call<WeatherInfo> call, Throwable t) {

                                }
                            });


                        }
                    }

                    @Override
                    public void onFailure(Call<DetailsResult> call, Throwable t) {

                    }
                });

            }
        });

        return view;
    }

}
