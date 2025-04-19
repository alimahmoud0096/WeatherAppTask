package com.alihafez.weatherapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alihafez.core.presentation.Screens
import com.alihafez.home.presentation.HomeRoute
import com.alihafez.search.presentation.SearchRoute
import com.alihafez.settings.presentation.SettingsRoute

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Composable
fun WeatherNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    NavHost(
        modifier = modifier.statusBarsPadding(),
        navController = navHostController,
        startDestination = Screens.Home
    ) {
        composable<Screens.Home> {
            HomeRoute()
        }
        composable<Screens.Search> {
            SearchRoute()
        }

        composable<Screens.Settings> {
            SettingsRoute()
        }
    }
}