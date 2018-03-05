package vivenkko.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import vivenkko.weather.model.forecast.ForecastInfo;

import java.util.List;

public class MyForecastInfoRecyclerViewAdapter extends RecyclerView.Adapter<MyForecastInfoRecyclerViewAdapter.ViewHolder> {

    private final List<ForecastInfo> mValues;
    private final Context ctx;

    public MyForecastInfoRecyclerViewAdapter(List<ForecastInfo> items, Context context) {
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

        holder.cityName.setText(mValues.get(0).getList().get(0).getClass().getName());
        holder.description.setText(mValues.get(0).getList().get(0).getWeather().get(0).getDescription());
        holder.temperature.setText(mValues.get(0).getList().get(0).getMain().getTemp().toString()+"º C");
        holder.maxTemp.setText(mValues.get(0).getList().get(0).getMain().getTempMax().toString()+"º");
        holder.minTemp.setText(mValues.get(0).getList().get(0).getMain().getTempMin().toString()+"º");

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView cityName;
        public final TextView description;
        public final TextView temperature;
        public final TextView maxTemp;
        public final TextView minTemp;
        public final ImageView weatherBg;
        public ForecastInfo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            cityName = view.findViewById(R.id.textViewCityF);
            description = view.findViewById(R.id.textViewDescriptionF);
            temperature = view.findViewById(R.id.textViewTempF);
            maxTemp = view.findViewById(R.id.textViewMaxF);
            minTemp = view.findViewById(R.id.textViewMinF);
            weatherBg = view.findViewById(R.id.imageViewBackF);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + cityName.getText() + "'";
        }
    }
}
