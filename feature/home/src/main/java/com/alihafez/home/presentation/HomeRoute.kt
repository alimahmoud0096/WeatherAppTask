package com.alihafez.home.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alihafez.core.presentation.ScreenContainer
import com.alihafez.core.presentation.component.Subtitle
import com.alihafez.home.R
import com.alihafez.home.presentation.component.CurrentWeatherWidget
import com.alihafez.home.presentation.component.ForecastItem
import com.alihafez.core.presentation.component.OtherConditionsSection
import com.alihafez.core.presentation.preview.PreviewLightDarkMode

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.currentWeatherStateFlow.collectAsStateWithLifecycle()

    ScreenContainer(
        modifier = modifier.fillMaxSize(),
        isLoading = state.isLoading,
        errorDialogText = state.hasError,
        onDialogButtonClick = {
            viewModel.hideError()
        }, screen = {
            HomeScreen(modifier = modifier, state = state)
        })
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, state: HomeUiState) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
    ) {
        item {
            CurrentWeatherWidget(
                modifier = Modifier,
                state = state.weatherUi
            )
            HorizontalDivider(thickness = 1.dp)
            OtherConditionsSection(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                state = state.weatherCondition
            )

            HorizontalDivider(thickness = 1.dp)
            Subtitle(
                text = stringResource(id = R.string.home_weekly_forecast_title)
            )
        }
        items(items = state.forecast) { forecastItem ->
            ForecastItem(
                forecast = forecastItem
            )
        }
    }
}

@PreviewLightDarkMode
@Composable
private fun HomeScreenPreview() {
    HomeScreen(state = HomeUiState())
}