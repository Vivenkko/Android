package com.jarteaga.googlenoseque.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jarteaga.googlenoseque.R;
import com.jarteaga.googlenoseque.models.Notas;

import java.util.List;

/**
 * Created by jaduran on 23/02/2018.
 */

public class EditNotasAdapter extends RecyclerView.Adapter<EditNotasAdapter.ViewHolder> {

    private final List<Notas> mValues;
    private Context ctx;

    public EditNotasAdapter(Context context, List<Notas> items) {
        ctx = context;
        mValues = items;
    }

    @Override
    public EditNotasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_notaslist, parent, false);
        return new EditNotasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EditNotasAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView titulo;
        public final TextView descripcion;
        public final TextView categoria;
        public Notas mItem;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            titulo = (TextView) view.findViewById(R.id.textViewTitutlo);
            descripcion = (TextView) view.findViewById(R.id.textViewDescripcion);
            categoria = view.findViewById(R.id.textViewCategoria);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + descripcion.getText() + "'";
        }
    }
}
