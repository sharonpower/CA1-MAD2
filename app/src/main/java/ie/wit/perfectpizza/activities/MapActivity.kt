package ie.wit.perfectpizza.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ie.wit.perfectpizza.R
import ie.wit.perfectpizza.databinding.ActivityMapBinding
import ie.wit.perfectpizza.models.Destination

class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapBinding
    var destination = Destination()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        destination = intent.extras?.getParcelable<Destination>("destination")!!

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val dest = LatLng(destination.lat, destination.lng)
        val options = MarkerOptions()
            .title("Pizza Place")
            .snippet("GPS : $dest")
            .draggable(true)
            .position(dest)
        map.addMarker(options)
        map.setOnMarkerDragListener(this)
        map.setOnMarkerClickListener(this)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(dest, destination.zoom))
        map.getUiSettings().setZoomControlsEnabled(true)
        // zoom control reference : https://stackoverflow.com/questions/17412882/positioning-google-maps-v2-zoom-in-controls-in-android
    }

    override fun onMarkerDragStart(marker: Marker) {

    }

    override fun onMarkerDrag(marker: Marker) {

    }


    override fun onMarkerDragEnd(marker: Marker) {
        destination.lat = marker.position.latitude
        destination.lng = marker.position.longitude
        destination.zoom = map.cameraPosition.zoom
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val dest = LatLng(destination.lat, destination.lng)
        marker.snippet = "GPS : $dest"
        return false
    }

    override fun onBackPressed() {
        val resultIntent = Intent()
        resultIntent.putExtra("destination", destination)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
        super.onBackPressed()
    }
}
