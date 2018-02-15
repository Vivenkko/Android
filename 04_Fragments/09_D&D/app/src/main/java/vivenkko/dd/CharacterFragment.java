package vivenkko.dd;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

//
public class CharacterFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    List<DDCharacter> listCharacter;
    MyCharacterRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CharacterFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_list, container, false);

        listCharacter = new ArrayList<>();
        listCharacter.add(new DDCharacter("Redgar","https://i.ytimg.com/vi/ho15Mf6h_-g/maxresdefault.jpg",
                "Human Warrior", "Redgar Stonesword"));
        listCharacter.add(new DDCharacter("Krusk","https://vignette.wikia.nocookie.net/iron-throne-rp/images/2/26/Tormo.jpg/revision/latest?cb=20150209044340",
                "Half-orc Barbarian","Krusk Sauriancrusher"));
        listCharacter.add(new DDCharacter("Soveliss","https://geekandsundry.com/wp-content/uploads/2017/01/Rogue-Ranger-Archetypes-FI-970x545.png",
                "Half-elf Ranger", "Soveliss Lightwind"));


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MyCharacterRecyclerViewAdapter(getActivity(), listCharacter);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }


}
