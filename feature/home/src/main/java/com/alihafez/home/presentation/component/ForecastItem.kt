package com.alihafez.home.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alihafez.core.R
import com.alihafez.core.presentation.common.WeatherUi
import com.alihafez.core.presentation.getWeatherIconForPreview
import com.alihafez.core.presentation.preview.PreviewLightDarkMode
import com.alihafez.home.presentation.ForecastUi

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Composable
fun ForecastItem(
    modifier: Modifier = Modifier,
    forecast: ForecastUi
) {

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(4.dp),

    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(vertical = 4.dp, horizontal = 4.dp)

        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = modifier.padding(horizontal = 8.dp, vertical = 0.dp)
            ) {
                Text(
                    text = forecast.weather.date,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = forecast.weather.weather,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(forecast.weather.weatherIcon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(48.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(horizontal = 16.dp, vertical = 2.dp)
            ) {
                Text(
                    text = stringResource(
                        id = R.string.format_temperature,
                        forecast.weather.currentTemp

                    ),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = stringResource(
                        id = R.string.format_high_low_temperature,
                        forecast.weather.maxTemp, forecast.weather.minTemp
                    ),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }

        ForecastMoreDetailsSection(forecastUi = forecast)


    }
}

@PreviewLightDarkMode
@Composable
private fun ForecastItemPreview() {

    ForecastItem(
        forecast = ForecastUi(
            weather = WeatherUi(
                weather = "conditionText",
                date = "forecastDate",
                maxTemp = 90.0,
                minTemp = 40.0,
                weatherId = getWeatherIconForPreview()
            )
        )
    )
}
