package codes.zaak.showcase_mvvm_android.weather.data

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepository
@Inject constructor(val service: WeatherService) {
    fun fetchWeather(id: Int): Single<List<Forecast>> {
        return service.getWeather(id)
            .subscribeOn(Schedulers.io())
            .retry(3)
            .observeOn(Schedulers.computation())
    }
}