package codes.zaak.showcase_mvvm_android.di

import codes.zaak.showcase_mvvm_android.locationlist.ui.LocationFragment
import codes.zaak.showcase_mvvm_android.weather.ui.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeLocationFragment(): LocationFragment

    @ContributesAndroidInjector
    internal abstract fun contributeWeatherFragment(): WeatherFragment
}