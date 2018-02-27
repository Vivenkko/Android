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
import android.widget.ImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vivenkko.inote.model.Note;
import vivenkko.inote.retrofit.ApiKeeperService;
import vivenkko.inote.retrofit.IOnNoteInteractionListener;

public class NoteFragment extends Fragment implements IOnNoteInteractionListener {
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
        return view;
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

    @Override
    public void onNoteDobleClick(Note note) {
        ImageView trash = getActivity().findViewById(R.id.imageViewTrash);
        trash.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTrashNoteClick(Note note) {


    }
}
