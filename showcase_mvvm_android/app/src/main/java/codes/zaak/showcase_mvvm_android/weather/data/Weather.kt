package codes.zaak.showcase_mvvm_android.weather.data

import com.squareup.moshi.Json

data class Weather(
    val title: String,
    @Json(name = "consolidated_weather")
    val forecastList: List<Forecast>
)

data class Forecast(
    @Json(name = "weather_state_name")
    val state: String,
    @Json(name = "weather_state_abbr")
    val iconName: String,
    @Json(name = "max_temp")
    val maxTemp: Double
)