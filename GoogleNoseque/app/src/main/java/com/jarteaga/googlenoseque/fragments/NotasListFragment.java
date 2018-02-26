package com.jarteaga.googlenoseque.fragments;

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
import android.widget.Toast;

import com.jarteaga.googlenoseque.LoginActivity;
import com.jarteaga.googlenoseque.MainActivity;
import com.jarteaga.googlenoseque.R;

import com.jarteaga.googlenoseque.models.Notas;
import com.jarteaga.googlenoseque.retrofit.ApiController;
import com.jarteaga.googlenoseque.retrofit.ServiceGenerator;
import com.jarteaga.googlenoseque.retrofit.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotasListFragment extends Fragment {
    ImageView editImage;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    List<Notas> listadoNotas;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotasListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotasListFragment newInstance(int columnCount) {
        NotasListFragment fragment = new NotasListFragment();
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
        final View view = inflater.inflate(R.layout.fragment_notaslist_list, container, false);


        String key = getArguments().getString("apikey");

        ApiController api = ServiceGenerator.createService(ApiController.class);

        Call<List<Notas>> call = api.listNotas(key);

        call.enqueue(new Callback<List<Notas>>() {
            @Override
            public void onResponse(Call<List<Notas>> call, Response<List<Notas>> response) {
                if (response.isSuccessful()){
                    List<Notas> notas = response.body();
                    if (view instanceof RecyclerView) {
                        Context context = view.getContext();
                        RecyclerView recyclerView = (RecyclerView) view;
                        if (mColumnCount <= 1) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        } else {
                            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                        }
                        recyclerView.setAdapter(new MyNotasListRecyclerViewAdapter(getActivity(), notas));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Notas>> call, Throwable t) {
                Log.d("problema", "error");
            }
        });

        // Set the adapter

        return view;
    }


}
