package vivenkko.inote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vivenkko.inote.model.Note;


public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;
    private final Context ctx;
    private IOnNoteClick mListener;

    public MyNoteRecyclerViewAdapter(List<Note> items, Context context, IOnNoteClick iOnNoteClick) {
        mValues = items;
        ctx = context;
        mListener = iOnNoteClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.title.setText(holder.mItem.getTitle());
        holder.category.setText(holder.mItem.getCategory().getName());
        holder.description.setText(holder.mItem.getDescription());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.deleteNote(holder.mItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final TextView category;
        public final TextView description;
        public final ImageButton delete;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.textViewTitulo);
            category = view.findViewById(R.id.textViewCategory);
            description = view.findViewById(R.id.textViewDescription);
            delete = view.findViewById(R.id.imageViewRemove);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
