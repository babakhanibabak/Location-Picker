package com.example.locationpicker.data.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.locationpicker.domain.model.LocationListItemModel

@Entity(tableName = "location")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val lat: Double,
    val lng: Double,
    val comment: String,
    val isFavorite: Boolean = false,
)

fun LocationEntity.toDomainModel() = LocationListItemModel(
    id = id,
    lat = lat,
    lng = lng,
    comment = comment,
    isFavorite = isFavorite
)
