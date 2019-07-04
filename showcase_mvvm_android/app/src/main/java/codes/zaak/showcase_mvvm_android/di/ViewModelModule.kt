package codes.zaak.showcase_mvvm_android.di

import androidx.lifecycle.ViewModel
import codes.zaak.showcase_mvvm_android.locationlist.ui.LocationViewModel
import codes.zaak.showcase_mvvm_android.weather.ui.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LocationViewModel::class)
    internal abstract fun bindLocationViewModels(locationViewModel: LocationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    internal abstract fun bindWeatherListViewModels(weatherViewModel: WeatherViewModel): ViewModel
}