package codes.zaak.showcase_mvvm_android

import android.app.Activity
import android.app.Application
import codes.zaak.showcase_mvvm_android.di.AppInjector
import codes.zaak.showcase_mvvm_android.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchAndroidInjection: DispatchingAndroidInjector<Activity>

    private val appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchAndroidInjection
    }
}