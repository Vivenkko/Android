package vivenkko.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
    TextView cityName, temperature, minTemp, maxTemp, description;
    ImageView backgroundCity;

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
        autoComplete.setAdapter(new GooglePlacesResultAdapter(getActivity()));
        cityName = view.findViewById(R.id.textViewCityW);
        temperature = view.findViewById(R.id.textViewTempW);
        minTemp = view.findViewById(R.id.textViewMinW);
        maxTemp = view.findViewById(R.id.textViewMaxW);
        description = view.findViewById(R.id.textViewDescriptionW);
        backgroundCity = view.findViewById(R.id.imageViewCityW);

        backgroundCity.setScaleType(ImageView.ScaleType.CENTER_CROP);
        backgroundCity.setAlpha((float) 0.6);
        Picasso
                .with(getActivity())
                .load("http://www.ilondra.it/wp-content/uploads/2015/01/Greenwich-Park.jpg")
                .into(backgroundCity);

        ApiOpenWeather apiOpenWeather = ServiceGeneratorWeather.createService(ApiOpenWeather.class);
        Call<WeatherInfo> call = apiOpenWeather.getWeatherInfoByLatLng(51.5085300,-0.1257400);

        call.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                if(response.isSuccessful()){
                    cityName.setText(response.body().getName());
                    temperature.setText(response.body().getMain().getTemp().toString()+"º C");
                    minTemp.setText(response.body().getMain().getTempMin().toString()+"º");
                    maxTemp.setText(response.body().getMain().getTempMax().toString()+"º");
                    description.setText(response.body().getWeather().get(0).getDescription());

                }
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {

            }

        });

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

                            if (result.getResult().getPhotos() != null) {
                                if (!result.getResult().getPhotos().isEmpty()) {
                                    String photo_url = String.format("https://maps.googleapis.com/maps/api/place/photo?maxwidth=1000&key=AIzaSyD_FMG1EnTUm3Ja7bSnlCV2VINLFq7rLMw&photoreference=%s", result.getResult().getPhotos().get(0).getPhoto_reference());
                                    Picasso
                                            .with(getActivity())
                                            .load(photo_url)
                                            .into(backgroundCity);
                                }
                            }

                            call2.enqueue(new Callback<WeatherInfo>() {
                                @Override
                                public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                                    if (response.isSuccessful()){
                                        WeatherInfo resultWeather = response.body();

                                        cityName.setText(resultWeather.getName());
                                        temperature.setText(resultWeather.getMain().getTemp().toString()+"º C");
                                        maxTemp.setText(resultWeather.getMain().getTempMax().toString()+"º");
                                        minTemp.setText(resultWeather.getMain().getTempMin().toString()+"º");
                                        description.setText(resultWeather.getWeather().get(0).getDescription());
                                    }
                                }

                                @Override
                                public void onFailure(Call<WeatherInfo> call, Throwable t) {
                                    Toast.makeText(getActivity(), "Callback<WeatherInfo> failure", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<DetailsResult> call, Throwable t) {
                        Toast.makeText(getActivity(), "Callback<Detailsresult> failure", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }

    public static WeatherFragment newInstance() {
        WeatherFragment weatherFragment = new WeatherFragment();
        return weatherFragment;
    }
}
