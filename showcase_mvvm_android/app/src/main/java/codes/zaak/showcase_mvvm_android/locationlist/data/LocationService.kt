package codes.zaak.showcase_mvvm_android.locationlist.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {
    @GET("location/search")
    fun getLocations(@Query(value = "lattlong") latLong: String): Single<List<Location>>
}