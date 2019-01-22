package org.ups.greensky.location

import io.reactivex.Observable

interface LocationProvider {
    fun getCurrentLocation() : Observable<Location>
}