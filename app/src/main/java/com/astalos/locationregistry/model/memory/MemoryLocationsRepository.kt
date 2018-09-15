package com.astalos.locationregistry.model.memory

import com.astalos.locationregistry.domain.entities.SimpleLocation
import com.astalos.locationregistry.domain.entities.UserLocation
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.ILocationsRepository
import com.astalos.locationregistry.domain.repository.OneOf
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/4/18.
 */
class MemoryLocationsRepository @Inject constructor(): ILocationsRepository {
    private val locations = mutableListOf<UserLocation>()

    override fun locations(userId: Int): OneOf<Failure, List<UserLocation>> = OneOf.Success(locations.filter { it.userId == userId })

    override fun addLocation(location: UserLocation): OneOf<Failure, UserLocation> {
        val addedLocation = location.copy(id = locations.size + 1)
        locations.add(addedLocation)
        return OneOf.Success(addedLocation)
    }

    override fun getCurrentLocation(): OneOf<Failure, SimpleLocation> = OneOf.Success(SimpleLocation(1.0, 1.0))

}