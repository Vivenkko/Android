package vivenkko.inote;

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
import vivenkko.inote.model.Note;
import vivenkko.inote.retrofit.ApiKeeperService;
import vivenkko.inote.retrofit.ServiceGenerator;

public class NoteFragment extends Fragment implements IOnNoteClick {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    private IOnNoteClick mListener;

    public NoteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Por qué final?
        final View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        //Recupero la key para poder usarla en las peticiones
        String key = getArguments().getString("apikey");

        //Llamo a la API a través del servicio
        ApiKeeperService api = ServiceGenerator.createService(ApiKeeperService.class);

        //Creo la llamada del Login
        Call<List<Note>> call = api.listNotes(key);

        //Lanzo el Call
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                if (response.isSuccessful()){
                    // Si la petición ha tenido éxito, creo una lista para guardar las notas
                    // con los datos recogidos por la peticion
                    List<Note> notes = response.body();
                    // Si la vista es una instancia de RecyclerView
                    if (view instanceof RecyclerView) {
                        // Carga la vista con el contexto [solo los datos del activity] en un objeto context
                        Context context = view.getContext();
                        // Y carga la vista [solo la parte visual sin datos] en el recycler
                        RecyclerView recyclerView = (RecyclerView) view;

                        if (mColumnCount <= 1) {
                            // Si el layout es linear, carga el contexto sobre la vista
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        } else {
                            // En caso contrario carga el contexto sobre la vista un número de veces igual
                            // al número de elementos que tengas (cada cardView)
                            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                        }
                        // Por último settea el adapter con el constructor
                        recyclerView.setAdapter(new MyNoteRecyclerViewAdapter(notes, getActivity(),mListener));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                Log.d("There was a problem with the API call", "error");
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOnNoteClick) {
            mListener = (IOnNoteClick) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IOnNoteClick");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void deleteNote(Note note) {

    }
}
