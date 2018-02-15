package vivenkko.dd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyCharacterRecyclerViewAdapter extends RecyclerView.Adapter<MyCharacterRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<DDCharacter> mValues;

    public MyCharacterRecyclerViewAdapter(Context context, List<DDCharacter> items) {
        mValues = items;
        ctx = context;
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

        holder.name.setText(holder.mItem.getName());
        holder.classRace.setText(holder.mItem.getClassRace());
        holder.description.setText(holder.mItem.getDescription());

        // Seteamos la imagen en el componente ImageView
        Picasso.with(ctx)
                .load(holder.mItem.getImageURL())
                .resize(500,150)
                .centerCrop()
                .into(holder.imageURL);
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
            name = view.findViewById(R.id.textViewName);
            imageURL = view.findViewById(R.id.imageViewPhoto);
            classRace = view.findViewById(R.id.textViewRace);
            description = view.findViewById(R.id.textViewDescription);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name + "'";
        }
    }


}
