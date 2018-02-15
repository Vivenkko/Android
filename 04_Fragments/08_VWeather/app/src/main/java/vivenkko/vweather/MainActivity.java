package vivenkko.vweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.vweather.model.Forecast.ForecastInfo;
import vivenkko.vweather.model.Weather.WeatherInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Paso 1: Generar el servicio completo
        OpenweatherApi openweatherApi = ServiceGenerator.createService(OpenweatherApi.class);

        //Paso 2: Invocar al servicio concreto
        Call<WeatherInfo> peticion = openweatherApi.getWeatherInfoByCity("Ecija");
        Call<ForecastInfo> peticion2 = openweatherApi.getForecastInfoByCity("Ecija");

        //Paso 3: Invocar al método enqueue (ejecutar la petición asíncronamente)
        peticion.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                if(response.isSuccessful()) {
                    //Rescatamos los datos de la respuesta
                    WeatherInfo weatherInfo = response.body();
                    //Aquí "dibujamos" todos los datos en la interfaz de usuario
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

        peticion2.enqueue(new Callback<ForecastInfo>() {
            @Override
            public void onResponse(Call<ForecastInfo> call, Response<ForecastInfo> response) {
                if(response.isSuccessful()) {
                    //Rescatamos los datos de la respuesta
                    ForecastInfo forecastInfo = response.body();
                    //Aquí "dibujamos" todos los datos en la interfaz de usuario
                    Log.i("Retrofit OK", forecastInfo.toString());
                } else {
                    Toast.makeText(MainActivity.this, "Lo sentimos, pero ocurrió algún error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForecastInfo> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lo sentimos, pero ocurrió algún error de red", Toast.LENGTH_SHORT).show();
                Log.e("Retrofit Fail", t.toString());
            }
        });

    }
}
