package vivenkko.dd;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyCharacterRecyclerViewAdapter extends RecyclerView.Adapter<MyCharacterRecyclerViewAdapter.ViewHolder> {

    private final List<DDCharacter> mValues;

    public MyCharacterRecyclerViewAdapter(List<DDCharacter> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_character, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public final ImageView imageURL;
        public final TextView classRace;
        public final TextView description;

        public DDCharacter mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageURL = view.findViewById(R.id.);
            classRace = view.findViewById(R.id.);
            description = view.findViewById(R.id.);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name + "'";
        }
    }
}
