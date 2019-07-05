package codes.zaak.showcase_mvvm_android.locationlist.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocationRepository
@Inject constructor(private val service: LocationService) {
    fun fetchLocations(latLong: String): Single<List<Location>> {
        return service.getLocations(latLong)
            .subscribeOn(Schedulers.io())
            .retry(3)
            .observeOn(AndroidSchedulers.mainThread())
    }
}