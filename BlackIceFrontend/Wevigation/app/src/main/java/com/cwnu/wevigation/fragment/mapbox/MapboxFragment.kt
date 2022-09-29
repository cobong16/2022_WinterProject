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

    // 위치, 저장소 권한 획득을 위한 PermissionManager
    private val permissionsManager = PermissionsManager(this)

    private companion object {
        private const val BUTTON_ANIMATION_DURATION = 1500L
    }

    // Mapbox 지도 Object
    private lateinit var mapboxMap: MapboxMap

    // Layout Binding
    private lateinit var binding: FragmentMapboxBinding

    // 내비게이션 SDK에 의해 갱신되는 위치 제공
    private val navigationLocationProvider = NavigationLocationProvider()

    // Mapbox Navigation Entry Point
    private lateinit var mapboxNavigation: MapboxNavigation

    // NavigationCamera 실행을 위한 위치와 경로 데이터를 기반으로한 카메라 프레임 생성
    private lateinit var viewportDataSource: MapboxNavigationViewportDataSource

    // ViewportDataSource로 내비게이션 위치에 따른 카메라 이동
    private lateinit var navigationCamera: NavigationCamera

    // 스크린 사이즈에 맞도록 카메라 패딩
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

    // 다음 진행 경로를 알려주는 메뉴 생성
    private lateinit var maneuverApi: MapboxManeuverApi

    // 총 진행 상황을 알려주는 하단 바 생성
    private lateinit var tripProgressApi: MapboxTripProgressApi

    // 맵에 경로를 그리기 위해 데이터를 업데이트하는 클래스
    private lateinit var routeLineApi: MapboxRouteLineApi

    // 맵에 경로를 그려주는 클래스
    private lateinit var routeLineView: MapboxRouteLineView

    // 이동 방향 화살표를 그리그 위해 데이터를 업데이트하는 클래스
    private val routeArrowApi: MapboxRouteArrowApi = MapboxRouteArrowApi()

    // 이동 방향 화살표를 그려주는 클래스
    private lateinit var routeArrowView: MapboxRouteArrowView

    private val locationObserver = object : LocationObserver {
        var firstLocationUpdateReceived = false

        override fun onNewRawLocation(rawLocation: Location) {
            // 구현할 필요 없음
        }

        override fun onNewLocationMatcherResult(locationMatcherResult: LocationMatcherResult) {
            val enhancedLocation = locationMatcherResult.enhancedLocation
            // 지도에 현재 위치 퍽 생성하기
            navigationLocationProvider.changePosition(
                location = enhancedLocation,
                keyPoints = locationMatcherResult.keyPoints,
            )

            // 새 위치에 카메라 이동하기
            viewportDataSource.onLocationChanged(enhancedLocation)
            viewportDataSource.evaluate()

            // 위치 좌표를 받고
            // 현재 사용자 위치로 카메라 옮기기
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
        // 카메라 위치를 경로의 진행 상황에 따라 업데이트하기
        viewportDataSource.onRouteProgressChanged(routeProgress)
        viewportDataSource.evaluate()

        // 지도에 다음 방향 화살표 오버레이 그리기
        val style = mapboxMap.getStyle()
        if (style != null) {
            val maneuverArrowResult = routeArrowApi.addUpcomingManeuverArrow(routeProgress)
            routeArrowView.renderManeuverUpdate(style, maneuverArrowResult)
        }

        // 상단 다음 경로 카드 업데이트하기
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

        // 하단 총 진행 상황 업데이트하기
        binding.tripProgressView.render(
            tripProgressApi.getTripProgress(routeProgress)
        )
    }

    private val routesObserver = RoutesObserver { routeUpdateResult ->
        if (routeUpdateResult.routes.isNotEmpty()) {
            // 경로 지오메트리 비동기적으로 생성하고 그리기
            val routeLines = routeUpdateResult.routes.map { RouteLine(it, null) }

            routeLineApi.setRoutes(
                routeLines
            ) { value ->
                mapboxMap.getStyle()?.apply {
                    routeLineView.renderRouteDrawData(this, value)
                }
            }

            // 카메라 위치를 새 경로를 표현하기 위해 업데이트하기
            viewportDataSource.onRouteChanged(routeUpdateResult.routes.first())
            viewportDataSource.evaluate()
        } else {
            // 경로 선과 경로 화살표 제거하기
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

            // 카메라 위치 계산으로 부터 경로 레퍼런스 삭제
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

        // GPS 권한 얻기
        if (PermissionsManager.areLocationPermissionsGranted(fContext)) {
            requestStoragePermission()
        } else {
            permissionsManager.requestLocationPermissions(fContext as Activity?)
        }
        // View 바인딩
        binding = FragmentMapboxBinding.inflate(layoutInflater)

        mapboxMap = binding.mapView.getMapboxMap()

        // 위치 마커 생성
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

        // 내비게이션 지도 생성
        mapboxNavigation = if (MapboxNavigationProvider.isCreated()) {
            MapboxNavigationProvider.retrieve()
        } else {
            MapboxNavigationProvider.create(
                NavigationOptions.Builder(fContext.applicationContext)
                    .accessToken(getString(R.string.mapbox_access_token))
                    .build()
            )
        }

        // 내비게이션 카메라 시점 생성
        viewportDataSource = MapboxNavigationViewportDataSource(mapboxMap)
        navigationCamera = NavigationCamera(
            mapboxMap,
            binding.mapView.camera,
            viewportDataSource
        )

        // 내비게이션 카메라가 고정 될 수 있도록 Lifecycle listener 애니메이션 설정
        // 자동으로 지도에서 상호작용하여 사용자 위치를 추적함
        binding.mapView.camera.addCameraAnimationsLifecycleListener(
            NavigationBasicGesturesHandler(navigationCamera)
        )
        navigationCamera.registerNavigationCameraStateChangeObserver { navigationCameraState ->
            // 카메라 상태에 따라 UI 버튼들 숨기거나 표시하기
            when (navigationCameraState) {
                NavigationCameraState.TRANSITION_TO_FOLLOWING,
                NavigationCameraState.FOLLOWING -> binding.recenter.visibility = View.INVISIBLE
                NavigationCameraState.TRANSITION_TO_OVERVIEW,
                NavigationCameraState.OVERVIEW,
                NavigationCameraState.IDLE -> binding.recenter.visibility = View.VISIBLE
            }
        }
        // view 레이아웃과 스크린 방향에 따라 padding 값 설정하기
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

        // DistanceFormatterOptions 은 모든 기능에서 동일한 값을 사용하여야함
        val distanceFormatterOptions = mapboxNavigation.navigationOptions.distanceFormatterOptions

        // 상단 배너 뷰에 정보를 제공하는 다음 진행 경로 API 초기화
        maneuverApi = MapboxManeuverApi(
            MapboxDistanceFormatter(distanceFormatterOptions)
        )

        // 하단 진행 상황 뷰 초기화
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

        // 지도 경로 line 초기화하기
        val mapboxRouteLineOptions = MapboxRouteLineOptions.Builder(fContext)
            .withRouteLineBelowLayerId("road-label")
            .build()
        routeLineApi = MapboxRouteLineApi(mapboxRouteLineOptions)
        routeLineView = MapboxRouteLineView(mapboxRouteLineOptions)

        // 지도에 화살표를 그리기 위해 maneuver arrow view 초기화
        val routeArrowOptions = RouteArrowOptions.Builder(fContext).build()
        routeArrowView = MapboxRouteArrowView(routeArrowOptions)

        // map style 불러오기
        mapboxMap.loadStyleUri(
            "mapbox://styles/ys-jun/cl1yv028q003c14ojemxmxbou"
        ) {
            // 목적지 좌표로 경로 탐색을 위한 long click listener 생성
            binding.mapView.gestures.addOnMapLongClickListener { point ->
                findRoute(point)
                true
            }
        }

        // 상호작용 뷰 초기화
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

        // trip 세션 시작을 위해 자유 주행 모드에서 위치 정보 제공 받기
        // 경로 진행상황 또한 경로 설정 이후 정보를 제공받음
        mapboxNavigation.startTripSession()
        showBlackIce()

        return binding.root
    }

    // 매 5분 마다 백엔드와 통신하여 UI 그리기
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
                        blackIceText.text = "서버와의 통신에 실패했어요"
                        blackIceText.setBackgroundColor(resources.getColor(R.color.alert3))
                    }
                } else {
                    val blackIce = dataList[0]
                    val temp = dataList[1].toDouble()
                    val rainHour = dataList[2].toDouble()

                    activity?.runOnUiThread {
                        if (blackIce == "-1") {
                            blackIceText.text = "데이터를 불러 올 수 없어요"
                            blackIceText.setBackgroundColor(resources.getColor(R.color.alert3))
                        } else if (blackIce == "1") {
                            blackIceText.text = "도로 결빙에 주의하세요"
                            blackIceText.setBackgroundColor(resources.getColor(R.color.alert1))
                        } else if (blackIce == "2") {
                            blackIceText.text = "도로 결빙에 주의하세요"
                            blackIceText.setBackgroundColor(resources.getColor(R.color.alert0))
                        } else if (blackIce == "0" && rainHour == 0.0) {
                            blackIceText.text = "현재 예상되는 위험은 없어요"
                            blackIceText.setBackgroundColor(resources.getColor(R.color.teal_700))
                        } else {
                            blackIceText.text = "도로가 미끄러울 수 있어요"
                            blackIceText.setBackgroundColor(resources.getColor(R.color.alert2))
                        }
                    }
                }
            }
        }
    }

    // 백엔드와 통신하여 JSON 가져오기
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

    // 사용자 위치 반환
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

        // 이벤트 리스너 등록
        mapboxNavigation.registerRoutesObserver(routesObserver)
        mapboxNavigation.registerRouteProgressObserver(routeProgressObserver)
        mapboxNavigation.registerLocationObserver(locationObserver)
    }

    override fun onStop() {
        super.onStop()

        // 중지 시 모든 리스너 취소
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

        // SDK 자체 내장 최적 경로 탐색
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
                    // 구현할 필요 없음
                }

                override fun onCanceled(routeOptions: RouteOptions, routerOrigin: RouterOrigin) {
                    // 구현할 필요 없음
                }
            }
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fContext = context
    }

    private fun setRouteAndStartNavigation(routes: List<DirectionsRoute>) {
        // 탐색한 경로를 받아 경로 설정
        mapboxNavigation.setRoutes(routes)

        // UI 띄우기
        binding.routeOverview.visibility = View.VISIBLE
        binding.tripProgressCard.visibility = View.VISIBLE

        // 전체 경로로 카메라 줌 아웃하기
        navigationCamera.requestNavigationCameraToOverview()
    }

    private fun clearRouteAndStopNavigation() {
        // 경로 초기화
        mapboxNavigation.setRoutes(listOf())

        // UI 지우기
        binding.maneuverView.visibility = View.INVISIBLE
        binding.routeOverview.visibility = View.INVISIBLE
        binding.tripProgressCard.visibility = View.INVISIBLE
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