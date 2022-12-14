package com.cwnu.wevigation.fragment.mapbox

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cwnu.wevigation.R
import com.cwnu.wevigation.databinding.FragmentMapboxBinding
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.api.directions.v5.models.Bearing
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.api.directions.v5.models.RouteOptions
import com.mapbox.geojson.Point
import com.mapbox.maps.EdgeInsets
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.animation.camera
import com.mapbox.maps.plugin.gestures.gestures
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.navigation.base.TimeFormat
import com.mapbox.navigation.base.extensions.applyDefaultNavigationOptions
import com.mapbox.navigation.base.extensions.applyLanguageAndVoiceUnitOptions
import com.mapbox.navigation.base.options.NavigationOptions
import com.mapbox.navigation.base.route.RouterCallback
import com.mapbox.navigation.base.route.RouterFailure
import com.mapbox.navigation.base.route.RouterOrigin
import com.mapbox.navigation.core.MapboxNavigation
import com.mapbox.navigation.core.MapboxNavigationProvider
import com.mapbox.navigation.core.directions.session.RoutesObserver
import com.mapbox.navigation.core.formatter.MapboxDistanceFormatter
import com.mapbox.navigation.core.trip.session.LocationMatcherResult
import com.mapbox.navigation.core.trip.session.LocationObserver
import com.mapbox.navigation.core.trip.session.RouteProgressObserver
import com.mapbox.navigation.ui.maneuver.api.MapboxManeuverApi
import com.mapbox.navigation.ui.maps.camera.NavigationCamera
import com.mapbox.navigation.ui.maps.camera.data.MapboxNavigationViewportDataSource
import com.mapbox.navigation.ui.maps.camera.lifecycle.NavigationBasicGesturesHandler
import com.mapbox.navigation.ui.maps.camera.state.NavigationCameraState
import com.mapbox.navigation.ui.maps.camera.transition.NavigationCameraTransitionOptions
import com.mapbox.navigation.ui.maps.location.NavigationLocationProvider
import com.mapbox.navigation.ui.maps.route.arrow.api.MapboxRouteArrowApi
import com.mapbox.navigation.ui.maps.route.arrow.api.MapboxRouteArrowView
import com.mapbox.navigation.ui.maps.route.arrow.model.RouteArrowOptions
import com.mapbox.navigation.ui.maps.route.line.api.MapboxRouteLineApi
import com.mapbox.navigation.ui.maps.route.line.api.MapboxRouteLineView
import com.mapbox.navigation.ui.maps.route.line.model.MapboxRouteLineOptions
import com.mapbox.navigation.ui.maps.route.line.model.RouteLine
import com.mapbox.navigation.ui.tripprogress.api.MapboxTripProgressApi
import com.mapbox.navigation.ui.tripprogress.model.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import kotlin.concurrent.timer

class MapboxFragment : Fragment(), PermissionsListener {

    private lateinit var fContext: Context

    // ??????, ????????? ?????? ????????? ?????? PermissionManager
    private val permissionsManager = PermissionsManager(this)

    private companion object {
        private const val BUTTON_ANIMATION_DURATION = 1500L
    }

    // Mapbox ?????? Object
    private lateinit var mapboxMap: MapboxMap

    // Layout Binding
    private lateinit var binding: FragmentMapboxBinding

    // ??????????????? SDK??? ?????? ???????????? ?????? ??????
    private val navigationLocationProvider = NavigationLocationProvider()

    // Mapbox Navigation Entry Point
    private lateinit var mapboxNavigation: MapboxNavigation

    // NavigationCamera ????????? ?????? ????????? ?????? ???????????? ??????????????? ????????? ????????? ??????
    private lateinit var viewportDataSource: MapboxNavigationViewportDataSource

    // ViewportDataSource??? ??????????????? ????????? ?????? ????????? ??????
    private lateinit var navigationCamera: NavigationCamera

    // ????????? ???????????? ????????? ????????? ??????
    private val pixelDensity = Resources.getSystem().displayMetrics.density
    private val overviewPadding: EdgeInsets by lazy {
        EdgeInsets(
            140.0 * pixelDensity,
            40.0 * pixelDensity,
            120.0 * pixelDensity,
            40.0 * pixelDensity
        )
    }
    private val landscapeOverviewPadding: EdgeInsets by lazy {
        EdgeInsets(
            30.0 * pixelDensity,
            380.0 * pixelDensity,
            110.0 * pixelDensity,
            20.0 * pixelDensity
        )
    }
    private val followingPadding: EdgeInsets by lazy {
        EdgeInsets(
            180.0 * pixelDensity,
            40.0 * pixelDensity,
            150.0 * pixelDensity,
            40.0 * pixelDensity
        )
    }
    private val landscapeFollowingPadding: EdgeInsets by lazy {
        EdgeInsets(
            30.0 * pixelDensity,
            380.0 * pixelDensity,
            110.0 * pixelDensity,
            40.0 * pixelDensity
        )
    }

    // ?????? ?????? ????????? ???????????? ?????? ??????
    private lateinit var maneuverApi: MapboxManeuverApi

    // ??? ?????? ????????? ???????????? ?????? ??? ??????
    private lateinit var tripProgressApi: MapboxTripProgressApi

    // ?????? ????????? ????????? ?????? ???????????? ?????????????????? ?????????
    private lateinit var routeLineApi: MapboxRouteLineApi

    // ?????? ????????? ???????????? ?????????
    private lateinit var routeLineView: MapboxRouteLineView

    // ?????? ?????? ???????????? ????????? ?????? ???????????? ?????????????????? ?????????
    private val routeArrowApi: MapboxRouteArrowApi = MapboxRouteArrowApi()

    // ?????? ?????? ???????????? ???????????? ?????????
    private lateinit var routeArrowView: MapboxRouteArrowView

    private val locationObserver = object : LocationObserver {
        var firstLocationUpdateReceived = false

        override fun onNewRawLocation(rawLocation: Location) {
            // ????????? ?????? ??????
        }

        override fun onNewLocationMatcherResult(locationMatcherResult: LocationMatcherResult) {
            val enhancedLocation = locationMatcherResult.enhancedLocation
            // ????????? ?????? ?????? ??? ????????????
            navigationLocationProvider.changePosition(
                location = enhancedLocation,
                keyPoints = locationMatcherResult.keyPoints,
            )

            // ??? ????????? ????????? ????????????
            viewportDataSource.onLocationChanged(enhancedLocation)
            viewportDataSource.evaluate()

            // ?????? ????????? ??????
            // ?????? ????????? ????????? ????????? ?????????
            if (!firstLocationUpdateReceived) {
                firstLocationUpdateReceived = true
                navigationCamera.requestNavigationCameraToOverview(
                    stateTransitionOptions = NavigationCameraTransitionOptions.Builder()
                        .maxDuration(0) // instant transition
                        .build()
                )
            }
        }
    }

    private val routeProgressObserver = RouteProgressObserver { routeProgress ->
        // ????????? ????????? ????????? ?????? ????????? ?????? ??????????????????
        viewportDataSource.onRouteProgressChanged(routeProgress)
        viewportDataSource.evaluate()

        // ????????? ?????? ?????? ????????? ???????????? ?????????
        val style = mapboxMap.getStyle()
        if (style != null) {
            val maneuverArrowResult = routeArrowApi.addUpcomingManeuverArrow(routeProgress)
            routeArrowView.renderManeuverUpdate(style, maneuverArrowResult)
        }

        // ?????? ?????? ?????? ?????? ??????????????????
        val maneuvers = maneuverApi.getManeuvers(routeProgress)
        maneuvers.fold(
            { error ->
                Toast.makeText(
                    context,
                    error.errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            },
            {
                binding.maneuverView.visibility = View.VISIBLE
                binding.maneuverView.renderManeuvers(maneuvers)
            }
        )

        // ?????? ??? ?????? ?????? ??????????????????
        binding.tripProgressView.render(
            tripProgressApi.getTripProgress(routeProgress)
        )
    }

    private val routesObserver = RoutesObserver { routeUpdateResult ->
        if (routeUpdateResult.routes.isNotEmpty()) {
            // ?????? ??????????????? ?????????????????? ???????????? ?????????
            val routeLines = routeUpdateResult.routes.map { RouteLine(it, null) }

            routeLineApi.setRoutes(
                routeLines
            ) { value ->
                mapboxMap.getStyle()?.apply {
                    routeLineView.renderRouteDrawData(this, value)
                }
            }

            // ????????? ????????? ??? ????????? ???????????? ?????? ??????????????????
            viewportDataSource.onRouteChanged(routeUpdateResult.routes.first())
            viewportDataSource.evaluate()
        } else {
            // ?????? ?????? ?????? ????????? ????????????
            val style = mapboxMap.getStyle()
            if (style != null) {
                routeLineApi.clearRouteLine { value ->
                    routeLineView.renderClearRouteLineValue(
                        style,
                        value
                    )
                }
                routeArrowView.render(style, routeArrowApi.clearArrows())
            }

            // ????????? ?????? ???????????? ?????? ?????? ???????????? ??????
            viewportDataSource.clearRouteData()
            viewportDataSource.evaluate()
        }
    }

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // GPS ?????? ??????
        if (PermissionsManager.areLocationPermissionsGranted(fContext)) {
            requestStoragePermission()
        } else {
            permissionsManager.requestLocationPermissions(fContext as Activity?)
        }
        // View ?????????
        binding = FragmentMapboxBinding.inflate(layoutInflater)

        mapboxMap = binding.mapView.getMapboxMap()

        // ?????? ?????? ??????
        binding.mapView.location.apply {
            this.locationPuck = LocationPuck2D(
                bearingImage = ContextCompat.getDrawable(
                    fContext,
                    com.mapbox.navigation.R.drawable.mapbox_navigation_puck_icon
                )
            )
            setLocationProvider(navigationLocationProvider)
            enabled = true
        }

        // ??????????????? ?????? ??????
        mapboxNavigation = if (MapboxNavigationProvider.isCreated()) {
            MapboxNavigationProvider.retrieve()
        } else {
            MapboxNavigationProvider.create(
                NavigationOptions.Builder(fContext.applicationContext)
                    .accessToken(getString(R.string.mapbox_access_token))
                    .build()
            )
        }

        // ??????????????? ????????? ?????? ??????
        viewportDataSource = MapboxNavigationViewportDataSource(mapboxMap)
        navigationCamera = NavigationCamera(
            mapboxMap,
            binding.mapView.camera,
            viewportDataSource
        )

        // ??????????????? ???????????? ?????? ??? ??? ????????? Lifecycle listener ??????????????? ??????
        // ???????????? ???????????? ?????????????????? ????????? ????????? ?????????
        binding.mapView.camera.addCameraAnimationsLifecycleListener(
            NavigationBasicGesturesHandler(navigationCamera)
        )
        navigationCamera.registerNavigationCameraStateChangeObserver { navigationCameraState ->
            // ????????? ????????? ?????? UI ????????? ???????????? ????????????
            when (navigationCameraState) {
                NavigationCameraState.TRANSITION_TO_FOLLOWING,
                NavigationCameraState.FOLLOWING -> binding.recenter.visibility = View.INVISIBLE
                NavigationCameraState.TRANSITION_TO_OVERVIEW,
                NavigationCameraState.OVERVIEW,
                NavigationCameraState.IDLE -> binding.recenter.visibility = View.VISIBLE
            }
        }
        // view ??????????????? ????????? ????????? ?????? padding ??? ????????????
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            viewportDataSource.overviewPadding = landscapeOverviewPadding
        } else {
            viewportDataSource.overviewPadding = overviewPadding
        }
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            viewportDataSource.followingPadding = landscapeFollowingPadding
        } else {
            viewportDataSource.followingPadding = followingPadding
        }

        // DistanceFormatterOptions ??? ?????? ???????????? ????????? ?????? ??????????????????
        val distanceFormatterOptions = mapboxNavigation.navigationOptions.distanceFormatterOptions

        // ?????? ?????? ?????? ????????? ???????????? ?????? ?????? ?????? API ?????????
        maneuverApi = MapboxManeuverApi(
            MapboxDistanceFormatter(distanceFormatterOptions)
        )

        // ?????? ?????? ?????? ??? ?????????
        tripProgressApi = MapboxTripProgressApi(
            TripProgressUpdateFormatter.Builder(fContext)
                .distanceRemainingFormatter(
                    DistanceRemainingFormatter(distanceFormatterOptions)
                )
                .timeRemainingFormatter(
                    TimeRemainingFormatter(fContext)
                )
                .percentRouteTraveledFormatter(
                    PercentDistanceTraveledFormatter()
                )
                .estimatedTimeToArrivalFormatter(
                    EstimatedTimeToArrivalFormatter(fContext, TimeFormat.NONE_SPECIFIED)
                )
                .build()
        )

        // ?????? ?????? line ???????????????
        val mapboxRouteLineOptions = MapboxRouteLineOptions.Builder(fContext)
            .withRouteLineBelowLayerId("road-label")
            .build()
        routeLineApi = MapboxRouteLineApi(mapboxRouteLineOptions)
        routeLineView = MapboxRouteLineView(mapboxRouteLineOptions)

        // ????????? ???????????? ????????? ?????? maneuver arrow view ?????????
        val routeArrowOptions = RouteArrowOptions.Builder(fContext).build()
        routeArrowView = MapboxRouteArrowView(routeArrowOptions)

        // map style ????????????
        mapboxMap.loadStyleUri(
            "mapbox://styles/ys-jun/cl1yv028q003c14ojemxmxbou"
        ) {
            // ????????? ????????? ?????? ????????? ?????? long click listener ??????
            binding.mapView.gestures.addOnMapLongClickListener { point ->
                findRoute(point)
                true
            }
        }

        // ???????????? ??? ?????????
        binding.stop.setOnClickListener {
            clearRouteAndStopNavigation()
        }
        binding.recenter.setOnClickListener {
            navigationCamera.requestNavigationCameraToFollowing()
            binding.routeOverview.showTextAndExtend(BUTTON_ANIMATION_DURATION)
        }
        binding.routeOverview.setOnClickListener {
            navigationCamera.requestNavigationCameraToOverview()
            binding.recenter.showTextAndExtend(BUTTON_ANIMATION_DURATION)
        }

        // trip ?????? ????????? ?????? ?????? ?????? ???????????? ?????? ?????? ?????? ??????
        // ?????? ???????????? ?????? ?????? ?????? ?????? ????????? ????????????
        mapboxNavigation.startTripSession()
        showBlackIce()

        return binding.root
    }

    // ??? 5??? ?????? ???????????? ???????????? UI ?????????
    private fun showBlackIce() {

        timer(period = 300000, initialDelay = 5000) {

            val blackIceText: TextView = binding.root.findViewById(R.id.blackIceWarn)

            thread(start=true) {

                val location: Location? = getLocation()
                val latitude: Double = location?.latitude ?: 37.541
                val longitude: Double = location?.longitude ?: 126.986

                val dataList: List<String> = getJson(latitude, longitude)

                if (dataList[0] == "null") {
                    activity?.runOnUiThread {
                        blackIceText.text = "???????????? ????????? ???????????????"
                        blackIceText.setBackgroundColor(resources.getColor(R.color.alert3))
                    }
                } else {
                    val blackIce = dataList[0]
                    val temp = dataList[1].toDouble()
                    val rainHour = dataList[2].toDouble()

                    activity?.runOnUiThread {
                        if (blackIce == "-1") {
                            blackIceText.text = "???????????? ?????? ??? ??? ?????????"
                            blackIceText.setBackgroundColor(resources.getColor(R.color.alert3))
                        } else if (blackIce == "1") {
                            blackIceText.text = "?????? ????????? ???????????????"
                            blackIceText.setBackgroundColor(resources.getColor(R.color.alert1))
                        } else if (blackIce == "2") {
                            blackIceText.text = "?????? ????????? ???????????????"
                            blackIceText.setBackgroundColor(resources.getColor(R.color.alert0))
                        } else if (blackIce == "0" && rainHour == 0.0) {
                            blackIceText.text = "?????? ???????????? ????????? ?????????"
                            blackIceText.setBackgroundColor(resources.getColor(R.color.teal_700))
                        } else {
                            blackIceText.text = "????????? ???????????? ??? ?????????"
                            blackIceText.setBackgroundColor(resources.getColor(R.color.alert2))
                        }
                    }
                }
            }
        }
    }

    // ???????????? ???????????? JSON ????????????
    private fun getJson(latitude: Double, longitude: Double): List<String> {
        try {
            val apiURL = "http://cobolnet.duckdns.org:62022/blackice?"
            val urlBuilder = apiURL + "lat=" + latitude + "&lng=" + longitude
            val url = URL(urlBuilder)
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
            val jsonData = jsonObject.getJSONObject("data")

            val tempList = mutableListOf<String>()
            tempList.add(0, jsonData.get("blackIce").toString())
            tempList.add(1, jsonData.get("temperature").toString())
            tempList.add(2,jsonData.get("rainHour").toString())

            Log.i("data", "get json Success")

            return tempList
        } catch (e: Exception) {
            val tempList = mutableListOf<String>()
            tempList.add(0, "null")
            tempList.add(1, "null")
            tempList.add(2, "null")

            Log.i("data", "get json failed")
            return tempList
        }
    }

    // ????????? ?????? ??????
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

    override fun onStart() {
        super.onStart()

        // ????????? ????????? ??????
        mapboxNavigation.registerRoutesObserver(routesObserver)
        mapboxNavigation.registerRouteProgressObserver(routeProgressObserver)
        mapboxNavigation.registerLocationObserver(locationObserver)
    }

    override fun onStop() {
        super.onStop()

        // ?????? ??? ?????? ????????? ??????
        mapboxNavigation.unregisterRoutesObserver(routesObserver)
        mapboxNavigation.unregisterRouteProgressObserver(routeProgressObserver)
        mapboxNavigation.unregisterLocationObserver(locationObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        MapboxNavigationProvider.destroy()
        maneuverApi.cancel()
        routeLineApi.cancel()
        routeLineView.cancel()
    }

    private fun findRoute(destination: Point) {
        val originLocation = navigationLocationProvider.lastLocation
        val originPoint = originLocation?.let {
            Point.fromLngLat(it.longitude, it.latitude)
        } ?: return

        // SDK ?????? ?????? ?????? ?????? ??????
        mapboxNavigation.requestRoutes(
            RouteOptions.builder()
                .applyDefaultNavigationOptions()
                .applyLanguageAndVoiceUnitOptions(fContext)
                .coordinatesList(listOf(originPoint, destination))
                // provide the bearing for the origin of the request to ensure
                // that the returned route faces in the direction of the current user movement
                .bearingsList(
                    listOf(
                        Bearing.builder()
                            .angle(originLocation.bearing.toDouble())
                            .degrees(45.0)
                            .build(),
                        null
                    )
                )
                .layersList(listOf(mapboxNavigation.getZLevel(), null))
                .build(),
            object : RouterCallback {
                override fun onRoutesReady(
                    routes: List<DirectionsRoute>,
                    routerOrigin: RouterOrigin
                ) {
                    setRouteAndStartNavigation(routes)
                }

                override fun onFailure(
                    reasons: List<RouterFailure>,
                    routeOptions: RouteOptions
                ) {
                    // ????????? ?????? ??????
                }

                override fun onCanceled(routeOptions: RouteOptions, routerOrigin: RouterOrigin) {
                    // ????????? ?????? ??????
                }
            }
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fContext = context
    }

    private fun setRouteAndStartNavigation(routes: List<DirectionsRoute>) {
        // ????????? ????????? ?????? ?????? ??????
        mapboxNavigation.setRoutes(routes)

        // UI ?????????
        binding.routeOverview.visibility = View.VISIBLE
        binding.tripProgressCard.visibility = View.VISIBLE

        // ?????? ????????? ????????? ??? ????????????
        navigationCamera.requestNavigationCameraToOverview()
    }

    private fun clearRouteAndStopNavigation() {
        // ?????? ?????????
        mapboxNavigation.setRoutes(listOf())

        // UI ?????????
        binding.maneuverView.visibility = View.INVISIBLE
        binding.routeOverview.visibility = View.INVISIBLE
        binding.tripProgressCard.visibility = View.INVISIBLE
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        Toast.makeText(
            fContext,
            "????????? ?????? ????????? ???????????????.",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            requestStoragePermission()
        } else {
            Toast.makeText(
                fContext,
                "????????? ????????? ????????? ?????????.",
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