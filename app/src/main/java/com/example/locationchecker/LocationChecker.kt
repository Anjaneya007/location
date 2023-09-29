package com.example.locationchecker

import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.mappls.sdk.maps.Mappls
import com.mappls.sdk.services.account.MapplsAccountManager

class LocationChecker : AppCompatActivity(), OnMapReadyCallback {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var btn:Button
    lateinit var status:TextView
    var lat:Double = 0.0
    var lon:Double = 0.0
    var latitude:Double = 0.0
    var longitude:Double=0.0
    val distance = FloatArray(1)
    lateinit var secoundLatlng:LatLng
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_checker)

        val name=intent.getStringExtra("name")
        latitude= intent.getDoubleExtra("latitude",0.0)
        longitude= intent.getDoubleExtra("longitude",0.0)

//        val firstLatlng=LatLng(latitude,longitude)




//        action bar
        supportActionBar?.title =name

        val actionBar: ActionBar?
        actionBar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#800080"))
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(colorDrawable)
        }

        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)

        getLocation()

        btn=findViewById(R.id.checkIt)
        status=findViewById(R.id.status)
        btn.setOnClickListener {
            getLocation()

            Location.distanceBetween(latitude,longitude,lat,lon,distance)
            var finalDistance:Float=distance[0]
//            status.text=finalDistance.toString()

            if(finalDistance<1000){
                status.text="Successful"
                status.setTextColor(Color.parseColor("#008000"));
            }else{
                status.text="Unsuccessful"
                status.setTextColor(Color.parseColor("#FF0000"));
            }



        }



    }

    private fun getLocation(){
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
            !=PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
        !=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100)
            return

        }
        val location=fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if(it!=null){
                lat=it.latitude
                lon=it.longitude

            }
        }

//        Toast.makeText(this,""+lat +" "+lon,Toast.LENGTH_SHORT).show()







    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

//        val name=intent.getStringExtra("name")
//        var latitude= intent.getStringExtra("latitude")!!.toDouble()
//        var longitude=intent.getStringExtra("longitude")!!.toDouble()
//
//        val firstLatlng=LatLng(latitude,longitude)
//
//        Location.distanceBetween(latitude,longitude,lat,lon,distance)
//        var finalDistance:Float=distance[0]
//
//        status=findViewById(R.id.status)
//        status.text=finalDistance.toString()





    }
}