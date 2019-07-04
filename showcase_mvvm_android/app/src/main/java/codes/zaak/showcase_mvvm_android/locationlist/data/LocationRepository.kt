package codes.zaak.showcase_mvvm_android.locationlist.data

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocationRepository
@Inject constructor(val service: LocationService) {
    fun fetchLocation(latLong: String): Single<List<Location>> {
        return service.getLocations(latLong)
            .subscribeOn(Schedulers.io())
            .retry(3)
            .observeOn(Schedulers.computation())
    }
}