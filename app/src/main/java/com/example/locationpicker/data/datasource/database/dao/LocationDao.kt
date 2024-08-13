package com.example.locationpicker.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.locationpicker.data.datasource.database.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

// Location Data Access Object
// CRUD (Create, Read, Update, Delete)
@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(list: List<LocationEntity>)

    @Query(SELECT_ALL)
    fun getAllLocationsFlow(): Flow<LocationEntity>

    @Query(SELECT_ALL)
    suspend fun getAllLocations(): List<LocationEntity>

    @Query(DELETE_LOCATION)
    suspend fun deleteLocation(id: Int)

    @Query("SELECT * FROM location WHERE isFavorite = 1")
    suspend fun getFavoriteLocations(): List<LocationEntity>

    @Update
    fun updateLocation(location: LocationEntity)

    companion object {
        private const val SELECT_ALL = "SELECT * FROM location"
        private const val DELETE_LOCATION = "DELETE FROM location WHERE id= :id"
    }
}