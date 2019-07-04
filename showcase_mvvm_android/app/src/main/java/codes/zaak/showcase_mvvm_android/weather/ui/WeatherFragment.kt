package codes.zaak.showcase_mvvm_android.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import codes.zaak.showcase_mvvm_android.R
import codes.zaak.showcase_mvvm_android.common.ui.AppViewModelFactory
import codes.zaak.showcase_mvvm_android.di.Injectable
import kotlinx.android.synthetic.main.fragment_location.*
import javax.inject.Inject

class WeatherFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var weatherViewModel: WeatherViewModel

    private var forecastAdapter = ForecastAdapter()
    private var locationId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview.apply {
            adapter = forecastAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        locationId = arguments?.getInt(ARG_WEATHER_ID, 0) ?: 0

        weatherViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)

        weatherViewModel.fetchWeather(locationId)

        weatherViewModel.forecastList.observe(this, Observer { forecastAdapter.submitList(it) })
    }

    override fun onDetach() {
        super.onDetach()
        weatherViewModel.clear()
    }

    companion object {
        const val TAG: String = "weather_fragment"
        private const val ARG_WEATHER_ID: String = "weather_id"

        fun showWeather(id: Int): Fragment {
            val fragment = WeatherFragment()
            val args = Bundle()
            args.putInt(ARG_WEATHER_ID, id)
            fragment.arguments = args
            return fragment
        }
    }
}