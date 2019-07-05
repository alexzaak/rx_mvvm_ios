package codes.zaak.showcase_mvvm_android.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import codes.zaak.showcase_mvvm_android.weather.data.Forecast
import codes.zaak.showcase_mvvm_android.weather.data.WeatherRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WeatherViewModel
@Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _forecastList = MutableLiveData<List<Forecast>>()
    val forecastList: LiveData<List<Forecast>> = _forecastList

    val disposable = CompositeDisposable()

    fun fetchWeather(id: Int) {
        disposable.add(
            weatherRepository.fetchWeather(id)
                .subscribe({ _forecastList.postValue(it) }, {})
        )
    }

    fun clear() {
        disposable.clear()
    }
}