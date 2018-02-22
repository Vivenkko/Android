package vivenkko.vweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vivenkko.vweather.model.Forecast.ForecastInfo;
import vivenkko.vweather.model.Interfaces.IOnForecastInfoListener;

import java.util.List;

public class MyForecastInfoRecyclerViewAdapter extends RecyclerView.Adapter<MyForecastInfoRecyclerViewAdapter.ViewHolder> {

    private final List<ForecastInfo> mValues;
    private Context ctx;
    private IOnForecastInfoListener mListener;

    public MyForecastInfoRecyclerViewAdapter(Context context, List<ForecastInfo> items, IOnForecastInfoListener listener) {
        mValues = items;
        ctx = context;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_forecastinfo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        // Set de la información de cada elemento en base al elemento actual
        // que se está dibujando

        holder.textViewDescription.setText(holder.mItem.getList().get(0).getMain().getTemp()+"º C");
        holder.textViewDescription.setText(holder.mItem.getList().get(0).getMain().getTempMax()+"º C");
        holder.textViewDescription.setText(holder.mItem.getList().get(0).getMain().getTempMin()+"º C");
        holder.textViewDescription.setText(holder.mItem.getList().get(0).getWeather().get(0).getDescription()+"º C");

        // Eventos
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCardClick(holder.mItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewTemp;
        public final TextView textViewDescription;
        public final TextView textViewMax;
        public final TextView textViewMin;
        public final ImageView imageViewBack;
        public ForecastInfo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewTemp = view.findViewById(R.id.textViewTemp);
            textViewMin = view.findViewById(R.id.textViewMin);
            textViewMax = view.findViewById(R.id.textViewMax);
            textViewDescription = view.findViewById(R.id.textViewDescription);
            imageViewBack = view.findViewById(R.id.imageViewBack);
        }

    }
}
