package codes.zaak.showcase_mvvm_android.locationlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import codes.zaak.showcase_mvvm_android.MainActivity
import codes.zaak.showcase_mvvm_android.R
import codes.zaak.showcase_mvvm_android.common.ui.AppViewModelFactory
import codes.zaak.showcase_mvvm_android.di.Injectable
import kotlinx.android.synthetic.main.fragment_location.*
import javax.inject.Inject


class LocationFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var viewModel: LocationViewModel
    private lateinit var locationAdapter: LocationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationAdapter = LocationAdapter {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(it.id)
            }
        }

        recyclerview.apply {
            adapter = locationAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LocationViewModel::class.java)
        viewModel.locationList.observe(this, Observer {
            locationAdapter.submitList(it)
        })

        viewModel.fetchLocations()
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.clear()
    }

    companion object {
        const val TAG: String = "location_fragment"
    }
}
