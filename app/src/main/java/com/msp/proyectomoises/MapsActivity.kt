package com.msp.proyectomoises


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.msp.proyectomoises.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    companion object{
      const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

       //Marcador para el Simarro.
        val simarro = LatLng(38.986472, -0.535433)
        mMap.addMarker(MarkerOptions().position(simarro).title("Marker in Simarro"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(simarro, 18f))
        val currentLatLong1 = LatLng(38.986972, -0.535375)
        val currentLatLong2 = LatLng(38.986939, -0.535443)
        val currentLatLong3 = LatLng(38.986904, -0.535576)
        val currentLatLong4 = LatLng(38.986259, -0.536187)
        val currentLatLong5 = LatLng(38.986236, -0.536270)
        val currentLatLong6 = LatLng(38.986209, -0.536340)
        val currentLatLong7 = LatLng(38.986326, -0.534908)
        val currentLatLong8 = LatLng(38.986303, -0.534987)
        val currentLatLong9 = LatLng(38.986274, -0.535064)

        placeMarker(currentLatLong1, "green")
        placeMarker(currentLatLong4, "green")
        placeMarker(currentLatLong7, "green")
        placeMarker(currentLatLong2, "blue")
        placeMarker(currentLatLong5, "blue")
        placeMarker(currentLatLong8, "blue")
        placeMarker(currentLatLong3, "yellow")
        placeMarker(currentLatLong6, "yellow")
        placeMarker(currentLatLong9, "yellow")

        enableLocation()
    }

    private fun placeMarker (location:LatLng, color:String){
        when(color){
            "green"->{
                val markerOption = MarkerOptions().position(location).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                mMap.addMarker(markerOption)
            }
            "blue"->{
                val markerOption = MarkerOptions().position(location).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                mMap.addMarker(markerOption)
            }
            "yellow"->{
                val markerOption = MarkerOptions().position(location).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                mMap.addMarker(markerOption)
            }
        }

    }



    private fun isLocationPermissionGranted()=
        ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    private fun enableLocation(){
        if (!::mMap.isInitialized)return
        if (isLocationPermissionGranted()){
            mMap.isMyLocationEnabled = true
        }else{
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this, R.string.TxtAcceptPermissionsSettings, Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

   @SuppressLint("MissingPermission")
   override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

       when(requestCode){
           REQUEST_CODE_LOCATION-> if (grantResults.isEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
               mMap.isMyLocationEnabled = true

           }else{
               Toast.makeText(this, R.string.TxtAcceptPermissionsSettings, Toast.LENGTH_SHORT).show()

           }
           else -> { super.onRequestPermissionsResult(requestCode, permissions, grantResults)}
       }
    }


}