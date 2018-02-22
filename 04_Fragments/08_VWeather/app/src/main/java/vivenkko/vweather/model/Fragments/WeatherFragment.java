package vivenkko.vweather.model.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.vweather.R;
import vivenkko.vweather.ServiceGenerator;
import vivenkko.vweather.model.Interfaces.OpenweatherApi;
import vivenkko.vweather.model.Weather.WeatherInfo;

public class WeatherFragment extends Fragment {
    TextView city, description, temp, min, max, cloudiness, wind, humidity, sunrise;
    TextView sunset, visibility;
    ImageView background;
    String url;

    private OnFragmentInteractionListener mListener;

    public WeatherFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        // Búsqueda de atributos
        city = view.findViewById(R.id.textViewCity);
        description = view.findViewById(R.id.textViewDescription);
        temp = view.findViewById(R.id.textViewTemp);
        min = view.findViewById(R.id.textViewMin);
        max = view.findViewById(R.id.textViewMax);
        cloudiness = view.findViewById(R.id.textViewCloudiness);
        wind = view.findViewById(R.id.textViewWind);
        humidity = view.findViewById(R.id.textViewHumidity);
        sunrise = view.findViewById(R.id.textViewSunrise);
        sunset = view.findViewById(R.id.textViewSunset);
        visibility = view.findViewById(R.id.textViewVisibility);
        background = view.findViewById(R.id.imageViewBack);

        //Paso 1: Generar el servicio completo
        OpenweatherApi openweatherApi = ServiceGenerator.createService(OpenweatherApi.class);

        //Paso 2: Invocar al servicio concreto
        Call<WeatherInfo> peticion = openweatherApi.getWeatherInfoByCity("Ecija");

        //Paso 3: Invocar al método enqueue (ejecutar la petición asíncronamente)
        peticion.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                if(response.isSuccessful()) {
                    //Rescatamos los datos de la respuesta
                    WeatherInfo weatherInfo = response.body();
                    //Aquí "dibujamos" todos los datos en la interfaz de usuario
                    city.setText(weatherInfo.getName());
                    if(weatherInfo.getWeather().size() > 0) {
                        description.setText(weatherInfo.getWeather().get(0).getDescription());
                    }
                    temp.setText(weatherInfo.getMain().getTemp()+"º C");
                    min.setText("Min.\n"+weatherInfo.getMain().getTempMin()+"º C");
                    max.setText("Max.\n"+weatherInfo.getMain().getTempMax()+"º C");
                    cloudiness.setText(weatherInfo.getClouds().getAll()+" %");
                    wind.setText(weatherInfo.getWind().getSpeed()+" mps");
                    humidity.setText(weatherInfo.getMain().getHumidity()+" %");
                    sunrise.setText(weatherInfo.getSys().getSunrise()+"H");
                    sunset.setText(weatherInfo.getSys().getSunset()+"H");
                    visibility.setText(weatherInfo.getVisibility()+" m");

                    switch (weatherInfo.getCod().toString()) {
                        case "800":
                            url = "https://www.ceramatec.com/sites/default/files/2016-11-11.jpg";
                            break;
                        case "801":
                            url = "http://www.dispatchlive.co.za/wp-content/uploads/2017/05/clear-skies.jpg";
                            break;
                        case "802":
                            url = "https://img00.deviantart.net/c199/i/2011/304/e/4/scattered_clouds___stock_by_thy_darkest_hour-d4em598.jpg";
                            break;
                        case "803":
                            url = "http://www.sgsweather.com/Images/Glossary1/Altocumulus%20cloud.jpg";
                            break;
                        case "804":
                            url = "http://www.gazetteseries.co.uk/resources/images/5360796.jpg?display=1&htype=0&type=responsive-gallery";
                            break;
                        case "50[0-4]":
                            url = "http://static.temblor.net/wp-content/uploads/2017/03/rain-1024x640.jpg";
                            break;
                        case "5[1-3][0-9]":
                            url = "https://metofficenews.files.wordpress.com/2012/10/thunder-and-lightning.jpg";
                            break;
                        case "6[0-9][0-9]":
                            url = "http://barkertherapyarts.com/wp-content/uploads/2014/02/1489-1248446570Quce.jpg";
                            break;
                        case "7[0-9][0-9]":
                            url = "http://podrobnosti.ua/media/pictures/2017/11/24/thumbs/740x415/foto-pixabay_rect_84216958625a702baaa888067299fed9.jpg";
                            break;
                    }

                    Picasso.with(getActivity())
                            .load(url)
                            .resize(500, 150)
                            .centerCrop()
                            .into(background);

                    Log.i("Retrofit OK", weatherInfo.toString());
                } else {
                    Toast.makeText(getActivity(), "Lo sentimos, pero ocurrió algún error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                Toast.makeText(getActivity(), "Lo sentimos, pero ocurrió algún error de red", Toast.LENGTH_SHORT).show();
                Log.e("Retrofit Fail", t.toString());
            }
        });

        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
