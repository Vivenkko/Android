package vivenkko.inote;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import vivenkko.inote.interfaces.ApiKeeperService;
import vivenkko.inote.interfaces.IOnNoteInteractionListener;
import vivenkko.inote.model.Note;


public class NoteFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private IOnNoteInteractionListener mListener;
    List<Note> noteList;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    Context ctx;

    public NoteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            //Aquí se inicia el retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://miguelcamposrivera.com/keeper/info")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiKeeperService service = retrofit.create(ApiKeeperService.class);
            String apiKey = "8wcok084w80go4gc0wksc8c8sw0sck8osgcwc4ok";

            Call<Note> noteCall = service.noteList(apiKey);

            adapter = new MyNoteRecyclerViewAdapter(noteList, mListener, ctx);
            recyclerView.setAdapter(adapter);

            noteCall.enqueue(new Callback<Note>() {
                @Override
                public void onResponse(Call<Note> call, Response<Note> response) {

                }

                @Override
                public void onFailure(Call<Note> call, Throwable t) {

                }
            });
        }
        return recyclerView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOnNoteInteractionListener) {
            mListener = (IOnNoteInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IOnNoteLnteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
