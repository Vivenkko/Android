package vivenkko.vweather.model.api;

import vivenkko.vweather.model.forecast.ForecastInfo;

/**
 * Created by viven on 16/02/2018.
 */

public interface IOnForecastInfoListener {
    void onCardClick(ForecastInfo forecastInfo);
}
