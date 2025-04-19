package com.alihafez.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.alihafez.core.presentation.Screens
import com.alihafez.core.presentation.common.CheckForPermissions
import com.alihafez.core.presentation.common.OnPermissionDenied
import com.alihafez.core.presentation.component.WeatherBottomNavigation
import com.alihafez.core.presentation.createLocationRequest
import com.alihafez.core.presentation.theme.WeatherAppTheme
import com.alihafez.weatherapp.component.InfoScreens
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    private val locationRequestLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                mainViewModel.setEvent(
                    MainContract.MainEvent
                        .CheckLocationSettings(isEnabled = true)
                )
            } else {
                mainViewModel.setEvent(
                    MainContract.MainEvent
                        .CheckLocationSettings(isEnabled = false)
                )
            }
        }
    private val permissionRequestLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            mainViewModel.setEvent(
                MainContract.MainEvent
                    .GrantPermission(isGranted = isGranted)
            )
        }

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // Get location updates.
    private val locationRequest = LocationRequest.Builder(30_000L)
        .setPriority(Priority.PRIORITY_HIGH_ACCURACY) // PRIORITY_BALANCED_POWER_ACCURACY
        .build()

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        createLocationRequest(
            activity = this@MainActivity,
            locationRequestLauncher = locationRequestLauncher
        ) {
            mainViewModel.setEvent(MainContract.MainEvent.CheckLocationSettings(isEnabled = true))
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            val isDarkTheme by mainViewModel.darkThemeFlow.collectAsStateWithLifecycle()
            WeatherAppTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()

                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }

                var shouldShowPermissionRationale by remember {
                    mutableStateOf(false)
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    bottomBar = {
                        WeatherBottomNavigation(onIconClick = {
                            navigateToTopLevelDestination(navController, it)
                        })
                    }
                ) { innerPadding ->
                    CheckForPermissions(
                        onPermissionGranted = {
                            mainViewModel.setEvent(
                                MainContract.MainEvent
                                    .GrantPermission(isGranted = true)
                            )
                            shouldShowPermissionRationale = false
                        },
                        onPermissionDenied = {
                            OnPermissionDenied(activityPermissionResult = permissionRequestLauncher)
                            shouldShowPermissionRationale = true
                        }
                    )
                    if (shouldShowPermissionRationale) {
                        LaunchedEffect(Unit) {
                            scope.launch {
                                val userAction = snackbarHostState.showSnackbar(
                                    message = "Please authorize location permissions",
                                    actionLabel = "Approve",
                                    duration = SnackbarDuration.Indefinite,
                                    withDismissAction = true
                                )
                                when (userAction) {
                                    SnackbarResult.ActionPerformed -> {
                                        shouldShowPermissionRationale = false
                                        permissionRequestLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                                    }

                                    SnackbarResult.Dismissed -> {
                                        shouldShowPermissionRationale = false
                                    }
                                }
                            }

                            fusedLocationProviderClient.requestLocationUpdates(
                                locationRequest, locationCallback,
                                Looper.myLooper()
                            )
                        }
                    }
                    InitScreen(
                        state = mainViewModel.state.value,
                        paddingValues = innerPadding,
                        navController
                    )

                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Composable
    private fun InitScreen(
        state: MainContract.MainUiState,
        paddingValues: PaddingValues,
        navController: NavHostController
    ) {
        when {
            state.isLocationSettingEnabled && state.isPermissionGranted -> {
                // Get the current location of the device.
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest, locationCallback,
                    Looper.myLooper()
                )
                //NavigationGraph(navController = navController, mainViewModel, paddingValues)
                WeatherNavigation(
                    navHostController = navController,
                    modifier = Modifier.padding(paddingValues)
                )

            }

            state.isLocationSettingEnabled && !state.isPermissionGranted -> {
                InfoScreens(message = R.string.location_no_permission_screen_description)

            }

            !state.isLocationSettingEnabled && !state.isPermissionGranted -> {
                InfoScreens(message = R.string.location_settings_not_enabled)

            }
        }
    }


    private fun navigateToTopLevelDestination(
        navController: NavHostController,
        topLevelDestination: Screens
    ) {
//    trace("Navigation: ${topLevelDestination.name}") {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (topLevelDestination) {
            Screens.Home -> navController.navigate(
                Screens.Home,
                topLevelNavOptions
            )

            Screens.Search -> {
                navController.navigate(
                    Screens.Search,
                    topLevelNavOptions
                )
            }

            Screens.Settings -> {
                navController.navigate(
                    Screens.Settings,
                    topLevelNavOptions
                )
            }
        }
    }


    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let { location: Location ->
                location.run {
                    mainViewModel.setEvent(
                        MainContract.MainEvent.SaveLocation(
                            location.latitude,
                            location.longitude
                        )
                    )
                }
            }
        }
    }



}
