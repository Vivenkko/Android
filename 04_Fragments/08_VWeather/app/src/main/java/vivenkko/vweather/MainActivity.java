package vivenkko.vweather;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.vweather.model.Weather.WeatherInfo;

public class MainActivity extends AppCompatActivity {

    TextView city;
    TextView description;
    TextView temp;
    TextView min;
    TextView max;
    TextView cloudiness;
    TextView wind;
    TextView humidity;
    TextView sunrise;
    TextView sunset;
    TextView visibility;
    ImageView background;
    String url;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Búsqueda de atributos
        city = findViewById(R.id.textViewCity);
        description = findViewById(R.id.textViewDescription);
        temp = findViewById(R.id.textViewTemp);
        min = findViewById(R.id.textViewMin);
        max = findViewById(R.id.textViewMax);
        cloudiness = findViewById(R.id.textViewCloudiness);
        wind = findViewById(R.id.textViewWind);
        humidity = findViewById(R.id.textViewHumidity);
        sunrise = findViewById(R.id.textViewSunrise);
        sunset = findViewById(R.id.textViewSunset);
        visibility = findViewById(R.id.textViewVisibility);
        background = findViewById(R.id.imageViewBack);





        //Paso 1: Generar el servicio completo
        OpenweatherApi openweatherApi = ServiceGenerator.createService(OpenweatherApi.class);

        //Paso 2: Invocar al servicio concreto
        Call<WeatherInfo> peticion = openweatherApi.getWeatherInfoByCity("Ecija");
        //Call<ForecastInfo> peticion2 = openweatherApi.getForecastInfoByCity("Ecija");

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

                    switch (weatherInfo.getWeather().get(0).getDescription()) {
                        case "cielo claro":
                            url = "https://www.ceramatec.com/sites/default/files/2016-11-11.jpg";
                            break;
                        case "few clouds":
                            url = "http://www.dispatchlive.co.za/wp-content/uploads/2017/05/clear-skies.jpg";
                            break;
                        case "scattered clouds":
                            url = "https://img00.deviantart.net/c199/i/2011/304/e/4/scattered_clouds___stock_by_thy_darkest_hour-d4em598.jpg";
                            break;
                        case "nubes rotas":
                            url = "http://www.gazetteseries.co.uk/resources/images/5360796.jpg?display=1&htype=0&type=responsive-gallery";
                            break;
                        case "lluvia ligera":
                            url = "http://www.appwrap.org/wp-content/uploads/2016/03/things-to-do-online-on-a-rainy-day.jpg";
                            break;
                        case "lluvia":
                            url = "http://static.temblor.net/wp-content/uploads/2017/03/rain-1024x640.jpg";
                            break;
                        case "tormenta":
                            url = "https://metofficenews.files.wordpress.com/2012/10/thunder-and-lightning.jpg";
                            break;
                        case "nieve":
                            url = "http://barkertherapyarts.com/wp-content/uploads/2014/02/1489-1248446570Quce.jpg";
                            break;
                        case "niebla":
                            url = "http://podrobnosti.ua/media/pictures/2017/11/24/thumbs/740x415/foto-pixabay_rect_84216958625a702baaa888067299fed9.jpg";
                            break;
                    }

                    Picasso.with(MainActivity.this)
                            .load(url)
                            .resize(500, 120)
                            .centerCrop()
                            .into(background);

                    Log.i("Retrofit OK", weatherInfo.toString());
                } else {
                    Toast.makeText(MainActivity.this, "Lo sentimos, pero ocurrió algún error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lo sentimos, pero ocurrió algún error de red", Toast.LENGTH_SHORT).show();
                Log.e("Retrofit Fail", t.toString());
            }
        });

//        peticion2.enqueue(new Callback<ForecastInfo>() {
//            @Override
//            public void onResponse(Call<ForecastInfo> call, Response<ForecastInfo> response) {
//                if(response.isSuccessful()) {
//                    //Rescatamos los datos de la respuesta
//                    ForecastInfo forecastInfo = response.body();
//                    //Aquí "dibujamos" todos los datos en la interfaz de usuario
//                    Log.i("Retrofit OK", forecastInfo.toString());
//                } else {
//                    Toast.makeText(MainActivity.this, "Lo sentimos, pero ocurrió algún error", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ForecastInfo> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Lo sentimos, pero ocurrió algún error de red", Toast.LENGTH_SHORT).show();
//                Log.e("Retrofit Fail", t.toString());
//            }
//        });

    }

}
