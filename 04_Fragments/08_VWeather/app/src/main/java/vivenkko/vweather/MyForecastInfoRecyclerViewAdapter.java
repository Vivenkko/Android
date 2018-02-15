package vivenkko.vweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import vivenkko.vweather.model.Forecast.ForecastInfo;

import java.util.List;

public class MyForecastInfoRecyclerViewAdapter extends RecyclerView.Adapter<MyForecastInfoRecyclerViewAdapter.ViewHolder> {

    private final List<ForecastInfo> mValues;
    private Context ctx;

    public MyForecastInfoRecyclerViewAdapter(Context context, List<ForecastInfo> items) {
        mValues = items;
        ctx = context;
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
        holder.textViewCity.setText(holder.textViewCity.getText());
        holder.textViewTemp.setText(holder.textViewTemp.getText());
        holder.textViewMin.setText(holder.textViewMin.getText());
        holder.textViewMax.setText(holder.textViewMax.getText());
        holder.textViewDescription.setText(holder.textViewDescription.getText());
       /* Picasso.with(ctx)
                .load(holder.mItem.getList().)
                .resize(500,150)
                .centerCrop()
                .into(holder.imageViewBack);*/

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewCity;
        public final TextView textViewTemp;
        public final TextView textViewDescription;
        public final TextView textViewMax;
        public final TextView textViewMin;
       // public final ImageView imageViewBack;
        public ForecastInfo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewCity = view.findViewById(R.id.textViewCity);
            textViewTemp = view.findViewById(R.id.textViewTemp);
            textViewMin = view.findViewById(R.id.textViewMin);
            textViewMax = view.findViewById(R.id.textViewMax);
            textViewDescription = view.findViewById(R.id.textViewDescription);
            //imageViewBack = view.findViewById(R.id.imageViewBack);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewCity.getText() + "'";
        }
    }
}
