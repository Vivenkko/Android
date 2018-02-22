package vivenkko.vweather.model.Interfaces;

import vivenkko.vweather.model.Forecast.ForecastInfo;

/**
 * Created by viven on 16/02/2018.
 */

public interface IOnForecastInfoListener {
    void onCardClick(ForecastInfo forecastInfo);
}
