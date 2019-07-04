package codes.zaak.showcase_mvvm_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import codes.zaak.showcase_mvvm_android.locationlist.ui.LocationFragment
import codes.zaak.showcase_mvvm_android.weather.ui.WeatherFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            //addFragment(Fragment(), WeatherFragment.TAG)

            val fragment = LocationFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, LocationFragment.TAG).commit()
        }
    }

    /** Shows the weather detail fragment  */
    fun show(id: Int) {
        val projectFragment = WeatherFragment.showWeather(id)

        supportFragmentManager
            .beginTransaction()
            .addToBackStack(WeatherFragment.TAG)
            .replace(
                R.id.fragment_container,
                projectFragment, null
            ).commit()
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchAndroidInjector
    }
}
