package codes.zaak.showcase_mvvm_android.di

import androidx.lifecycle.ViewModelProvider
import codes.zaak.showcase_mvvm_android.common.ui.AppViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: AppViewModelFactory): ViewModelProvider.Factory
}