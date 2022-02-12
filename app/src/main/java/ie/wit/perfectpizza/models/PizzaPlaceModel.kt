package ie.wit.perfectpizza.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PizzaPlaceModel(
    var id: Long = 0,
    var name: String = "",
    var location: String = "",
    var review: String = "",
    var image: Uri = Uri.EMPTY,
    var lat : Double = 0.0,
    var lng: Double = 0.0,
    var zoom: Float = 0f) : Parcelable

@Parcelize
data class Destination(
    var lat: Double = 0.0,
    var lng: Double = 0.0,
    var zoom: Float = 0f ) : Parcelable
