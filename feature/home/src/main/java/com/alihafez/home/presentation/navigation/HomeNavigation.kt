package com.alihafez.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alihafez.core.presentation.Screens
import com.alihafez.home.presentation.HomeRoute


fun NavGraphBuilder.homeRoute() {

    composable<Screens.Home>() {
        HomeRoute()
    }
}