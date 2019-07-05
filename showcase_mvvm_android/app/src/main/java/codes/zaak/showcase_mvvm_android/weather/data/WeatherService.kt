package codes.zaak.showcase_mvvm_android.weather.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("location/{id}/2019/7/05/")
    fun getWeather(@Path("id") id: Int): Single<List<Forecast>>
}