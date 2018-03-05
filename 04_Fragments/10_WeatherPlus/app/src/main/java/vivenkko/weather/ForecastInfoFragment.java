package vivenkko.weather;

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


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.weather.model.forecast.ForecastInfo;
import vivenkko.weather.model.retrofit.ApiOpenWeather;
import vivenkko.weather.model.retrofit.services.ServiceGeneratorWeather;

public class ForecastInfoFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    List<ForecastInfo> forecastInfoList;

    public ForecastInfoFragment() {
    }

    @SuppressWarnings("unused")
    public static ForecastInfoFragment newInstance(int columnCount) {
        ForecastInfoFragment fragment = new ForecastInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_forecastinfo_list, container, false);

        String key = getArguments().getString("apikey");
        ApiOpenWeather apiOpenWeather= ServiceGeneratorWeather.createService(ApiOpenWeather.class);

        Call<List<ForecastInfo>> call = apiOpenWeather.getForecastInfoByCity("London");
        call.enqueue(new Callback<List<ForecastInfo>>() {
            @Override
            public void onResponse(Call<List<ForecastInfo>> call, Response<List<ForecastInfo>> response) {
                if (response.isSuccessful()){
                    List<ForecastInfo> forecastInfoList = response.body();
                    if (view instanceof RecyclerView) {
                        Context context = view.getContext();
                        RecyclerView recyclerView = (RecyclerView) view;
                        if (mColumnCount <= 1) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        } else {
                            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                        }
                        recyclerView.setAdapter(new MyForecastInfoRecyclerViewAdapter(forecastInfoList, getActivity()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ForecastInfo>> call, Throwable t) {
                Log.d("There was a problem with the callback", "error");
            }
        });

        // Set the adapter
        return view;
    }

}
