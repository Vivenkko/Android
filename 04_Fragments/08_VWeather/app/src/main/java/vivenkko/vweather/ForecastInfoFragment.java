package vivenkko.vweather;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vivenkko.vweather.model.Forecast.ForecastInfo;

public class ForecastInfoFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private IOnForecastInfoListener mListener;
    List<ForecastInfo> forecastInfo;
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

            OpenweatherApi service = retrofit.create(OpenweatherApi.class);
            String city = "Ecija";

            Call<ForecastInfo> tallerCall = service.getForecastInfoByCity(city);

            tallerCall.enqueue(new Callback<ForecastInfo>() {
                @Override
                public void onResponse(Call<ForecastInfo> call, Response<ForecastInfo> response) {
                    //forecastInfo = response.body().;
                    recyclerView.setAdapter(new MyForecastInfoRecyclerViewAdapter(getActivity(),forecastInfo, mListener));
                }

                @Override
                public void onFailure(Call<ForecastInfo> call, Throwable t) {

                }
            });
        }

        return view;
    }




}
