package com.cwnu.wevigation.fragment.googleMaps

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.cwnu.wevigation.R
import com.cwnu.wevigation.databinding.FragmentGooglemapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class GoogleMapsFragment : Fragment(), OnMapReadyCallback, PermissionsListener {

    // 위치, 저장소 권한 획득을 위한 PermissionManager
    private val permissionsManager = PermissionsManager(this)

    private lateinit var binding: FragmentGooglemapsBinding
    private lateinit var fContext: Context
    private lateinit var mapView: MapView
    private lateinit var map: GoogleMap
    private lateinit var checkBoxFragment: CheckBoxFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGooglemapsBinding.inflate(layoutInflater)

        val sortButton: ImageButton = binding.root.findViewById(R.id.sort_button)

        sortButton.setOnClickListener {
            checkBoxFragment = CheckBoxFragment()
            checkBoxFragment.view
            checkBoxFragment.show(parentFragmentManager, "checkBoxFragment")
        }

        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            val arr = result?.split(",")

            showBlackIce(arr!!)}

        val gpsButton: ImageButton = binding.root.findViewById(R.id.gps_button)
        gpsButton.setOnClickListener {

            showCurrentLocation()
        }

        mapView = binding.root.findViewById(R.id.map)
        mapView.getMapAsync(this)

        return binding.root
    }

    private fun showBlackIce(arr_condition:List<String>) {

        thread(start = true) {
            try {
                val jsonArray: JSONArray = getJson()
                val markers = mutableListOf<LatLng>()
                val roadNames = mutableListOf<String>()
                val blackIces = mutableListOf<Int>()
                val rainHours = mutableListOf<Double>()
                val temperatures = mutableListOf<Double>()

                for(element in arr_condition) {
                    when (element) {
                        "정상" -> {

                            for (j in 0 until jsonArray.length()) {
                                if (jsonArray.getJSONObject(j)
                                        .getDouble("blackIce") == 0.0
                                ) {
                                    val latitude =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("latitude")
                                    val longitude =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("longitude")
                                    val roadName =
                                        jsonArray.getJSONObject(j)
                                            .getString("roadName")
                                    val blackIce =
                                        jsonArray.getJSONObject(j)
                                            .getInt("blackIce")
                                    val rainHour =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("rainHour")
                                    val temperature =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("temperature")
                                    val marker = LatLng(latitude, longitude)

                                    markers.add(marker)
                                    roadNames.add(roadName)
                                    blackIces.add(blackIce)
                                    rainHours.add(rainHour)
                                    temperatures.add(temperature)
                                }
                            }
                        }
                        "위험" -> {

                            for (j in 0 until jsonArray.length()) {
                                if (jsonArray.getJSONObject(j)
                                        .getDouble("blackIce") == 1.0
                                ) {
                                    val latitude =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("latitude")
                                    val longitude =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("longitude")
                                    val roadName =
                                        jsonArray.getJSONObject(j)
                                            .getString("roadName")
                                    val blackIce =
                                        jsonArray.getJSONObject(j)
                                            .getInt("blackIce")
                                    val rainHour =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("rainHour")
                                    val temperature =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("temperature")
                                    val marker = LatLng(latitude, longitude)

                                    markers.add(marker)
                                    roadNames.add(roadName)
                                    blackIces.add(blackIce)
                                    rainHours.add(rainHour)
                                    temperatures.add(temperature)
                                }
                            }
                        }
                        "매우위험" -> {
                            for (j in 0 until jsonArray.length()) {
                                if (jsonArray.getJSONObject(j)
                                        .getDouble("blackIce") == 2.0
                                ) {
                                    val latitude =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("latitude")
                                    val longitude =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("longitude")
                                    val roadName =
                                        jsonArray.getJSONObject(j)
                                            .getString("roadName")
                                    val blackIce =
                                        jsonArray.getJSONObject(j)
                                            .getInt("blackIce")
                                    val rainHour =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("rainHour")
                                    val temperature =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("temperature")
                                    val marker = LatLng(latitude, longitude)

                                    markers.add(marker)
                                    roadNames.add(roadName)
                                    blackIces.add(blackIce)
                                    rainHours.add(rainHour)
                                    temperatures.add(temperature)
                                }
                            }
                        }
                        "있음" -> {
                            for (j in 0 until jsonArray.length()) {
                                if (jsonArray.getJSONObject(j)
                                        .getDouble("rainHour") < 0.0
                                ) {
                                    val latitude =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("latitude")
                                    val longitude =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("longitude")
                                    val roadName =
                                        jsonArray.getJSONObject(j)
                                            .getString("roadName")
                                    val blackIce =
                                        jsonArray.getJSONObject(j)
                                            .getInt("blackIce")
                                    val rainHour =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("rainHour")
                                    val temperature =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("temperature")
                                    val marker = LatLng(latitude, longitude)

                                    markers.add(marker)
                                    roadNames.add(roadName)
                                    blackIces.add(blackIce)
                                    rainHours.add(rainHour)
                                    temperatures.add(temperature)
                                }
                            }
                        }
                        "없음" -> {
                            for (j in 0 until jsonArray.length()) {
                                if (jsonArray.getJSONObject(j)
                                        .getDouble("rainHour") == 0.0
                                ) {
                                    val latitude =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("latitude")
                                    val longitude =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("longitude")
                                    val roadName =
                                        jsonArray.getJSONObject(j)
                                            .getString("roadName")
                                    val blackIce =
                                        jsonArray.getJSONObject(j)
                                            .getInt("blackIce")
                                    val rainHour =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("rainHour")
                                    val temperature =
                                        jsonArray.getJSONObject(j)
                                            .getDouble("temperature")
                                    val marker = LatLng(latitude, longitude)

                                    markers.add(marker)
                                    roadNames.add(roadName)
                                    blackIces.add(blackIce)
                                    rainHours.add(rainHour)
                                    temperatures.add(temperature)
                                }
                            }
                        }

                    }
                }

                activity?.runOnUiThread {

                    map.clear()

                    for (i in 0 until markers.size) {
                        map.addMarker(MarkerOptions().position(markers[i])
                            .title("도로명: ${roadNames[i]} ")
                            .snippet("위험도: ${blackIces[i]} " +
                                    "기온: ${temperatures[i]} " +
                                    "강수량: ${rainHours[i]}"))
                    }

                }

            } catch (e: Exception) {
                Log.i("Exception", e.message.toString())
            }
        }

    }

    private fun getJson(): JSONArray {

        val apiURL = "http://cobolnet.duckdns.org:62022/blackice/highway"

        val url = URL(apiURL)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.setRequestProperty("Content-type", "application/json")
        connection.requestMethod = "GET"

        val rd: BufferedReader = if (connection.responseCode in 200..300) {
            BufferedReader(InputStreamReader(connection.inputStream))
        } else {
            BufferedReader(InputStreamReader(connection.errorStream))
        }

        val sb = StringBuilder()
        var line: String?

        while ((rd.readLine().also { line = it }) != null) {
            sb.append(line)
        }

        rd.close()
        connection.disconnect()

        val result = sb.toString()

        val jsonObject = JSONObject(result)
        val jsonArray = jsonObject.getJSONArray("data")

        return jsonArray
    }

    private fun showCurrentLocation() {
        val location: Location? = getLocation()

        val latitude: Double = location?.latitude ?: 37.541
        val longitude: Double = location?.longitude ?: 126.986

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), 10F))

    }

    private fun getLocation() : Location? {
        val context = fContext
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        var gpsLocation: Location? = null
        var networkLocation: Location? = null

        val isGPSEnabled: Boolean = locationManager
            .isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetEnabled: Boolean = locationManager
            .isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGPSEnabled && !isNetEnabled) {
            return null
        } else {
            val hasFineLocation = ContextCompat
                .checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            val hasCoarseLocation = ContextCompat
                .checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)

            if (hasFineLocation != PackageManager.PERMISSION_GRANTED ||
                hasCoarseLocation != PackageManager.PERMISSION_GRANTED) {
                return null
            }

            if (isNetEnabled) {
                networkLocation = locationManager
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            }

            if (isGPSEnabled) {
                gpsLocation = locationManager
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER)
            }

            return if (gpsLocation != null && networkLocation != null) {
                if (gpsLocation.accuracy > networkLocation.accuracy) {
                    gpsLocation
                } else {
                    networkLocation
                }
            } else {
                gpsLocation ?: networkLocation
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val center = LatLng(35.938427, 127.900733)
        map = googleMap
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 7F))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fContext = context
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView.onCreate(savedInstanceState)
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        Toast.makeText(
            fContext,
            "사용자 권한 확인이 필요합니다.",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            requestStoragePermission()
        } else {
            Toast.makeText(
                fContext,
                "사용자 권한을 확인해 주세요.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun requestStoragePermission() {
        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        val permissionsNeeded: MutableList<String> = ArrayList()
        if (
            ContextCompat.checkSelfPermission(fContext, permission) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            permissionsNeeded.add(permission)
            ActivityCompat.requestPermissions(
                fContext as Activity,
                permissionsNeeded.toTypedArray(),
                10
            )
        }
    }

}