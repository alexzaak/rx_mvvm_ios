package codes.zaak.showcase_mvvm_android.locationlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import codes.zaak.showcase_mvvm_android.locationlist.data.Location
import codes.zaak.showcase_mvvm_android.locationlist.data.LocationRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LocationViewModel
@Inject constructor(private val locationRepository: LocationRepository) : ViewModel() {

    private val _locationList = MutableLiveData<List<Location>>()
    val locationList: LiveData<List<Location>> = _locationList

    private val _onError = MutableLiveData<String>()
    val onError: LiveData<String> = _onError

    private val disposable = CompositeDisposable()

    fun fetchLocations() {
        disposable.add(
            locationRepository.fetchLocations("53.5582447,9.647645")
                .subscribe(
                    {
                        _locationList.postValue(it)
                    },
                    {
                        _onError.postValue(it.message)
                    }
                )
        )
    }

    fun clear() {
        disposable.clear()
    }
}