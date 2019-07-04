package codes.zaak.showcase_mvvm_android.weather.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("location/{id}")
    fun getWeather(@Path("id") id: Int): Single<Weather>
}