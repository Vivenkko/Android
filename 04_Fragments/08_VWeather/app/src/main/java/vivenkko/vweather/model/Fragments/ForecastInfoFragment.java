package vivenkko.vweather.model.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vivenkko.vweather.R;
import vivenkko.vweather.ServiceGenerator;
import vivenkko.vweather.model.Forecast.ForecastInfo;
import vivenkko.vweather.model.Interfaces.IOnForecastInfoListener;
import vivenkko.vweather.model.Interfaces.OpenweatherApi;

public class ForecastInfoFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    private IOnForecastInfoListener mListener;
    List<ForecastInfo> forecastInfoList;
    TextView city, description, temp, min, max;
    ImageView background;
    String url;
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ForecastInfoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecastinfo_list, container, false);

        // Búsqueda de atributos
        city = view.findViewById(R.id.textViewCity);
        description = view.findViewById(R.id.textViewDescription);
        temp = view.findViewById(R.id.textViewTemp);
        min = view.findViewById(R.id.textViewMin);
        max = view.findViewById(R.id.textViewMax);
        background = view.findViewById(R.id.imageViewBack);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://miguelcamposrivera.com/mecaround/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            //Paso 1: Generar el servicio completo
            OpenweatherApi openweatherApi = ServiceGenerator.createService(OpenweatherApi.class);

            //Paso 2: Invocar al servicio concreto
            Call<ForecastInfo> peticion2 = openweatherApi.getForecastInfoByCity("Ecija");

            peticion2.enqueue(new Callback<ForecastInfo>() {
                @Override
                public void onResponse(Call<ForecastInfo> call, Response<ForecastInfo> response) {
                    if(response.isSuccessful()) {
                        //Rescatamos los datos de la respuesta
                        ForecastInfo forecastInfo = response.body();
                        //Aquí "dibujamos" todos los datos en la interfaz de usuario
                        if(forecastInfo.getList().get(0).getWeather().size() > 0) {
                            description.setText(forecastInfo.getList().get(0).getWeather().get(0).getDescription());
                        }
                        temp.setText(forecastInfo.getList().get(0).getMain().getTemp()+"º C");
                        min.setText("Min.\n"+forecastInfo.getList().get(0).getMain().getTempMin()+"º C");
                        max.setText("Max.\n"+forecastInfo.getList().get(0).getMain().getTempMax()+"º C");

                        switch (forecastInfo.getList().get(0).getWeather().get(0).getDescription()) {
                            case "cielo claro":
                                url = "https://www.ceramatec.com/sites/default/files/2016-11-11.jpg";
                                break;
                            case "algo de nubes":
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
                                //temp.setTextColor();
                                url = "http://podrobnosti.ua/media/pictures/2017/11/24/thumbs/740x415/foto-pixabay_rect_84216958625a702baaa888067299fed9.jpg";
                                break;
                        }

                        Picasso.with(getActivity())
                                .load(url)
                                .resize(500, 150)
                                .centerCrop()
                                .into(background);

                        Log.i("Retrofit OK", forecastInfo.toString());
                    } else {
                        Toast.makeText(getActivity(), "Lo sentimos, pero ocurrió algún error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ForecastInfo> call, Throwable t) {

                }
            });
        }

        return view;
    }




}
