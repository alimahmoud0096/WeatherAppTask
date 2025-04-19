package com.alihafez.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alihafez.core.R
import com.alihafez.home.domain.model.HourlyForecast
import com.alihafez.core.domain.model.Main
import com.alihafez.core.presentation.preview.PreviewLightDarkMode

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Composable
fun HourlyDataElementRow(modifier: Modifier = Modifier, hourlyForecast: List<HourlyForecast>) {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        contentPadding = PaddingValues(horizontal = 8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(hourlyForecast) { item ->
            HourlyDataElement(hourlyForecast = item)
        }
    }
}

@Composable
fun HourlyDataElement(modifier: Modifier = Modifier, hourlyForecast: HourlyForecast) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 1.dp, vertical = 4.dp)
    ) {
        Text(
            text = stringResource(
                id = R.string.format_temperature,
                hourlyForecast.main.temp
            ),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Image(
            painter = painterResource(hourlyForecast.weatherIcon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
        )
        Text(
            text = hourlyForecast.hour,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@PreviewLightDarkMode
@Composable
private fun HourlyDataElementRowPreview() {
    HourlyDataElementRow(hourlyForecast = emptyList())
}

@PreviewLightDarkMode
@Composable
private fun HourlyDataElementPreview() {
    HourlyDataElement(
        hourlyForecast = HourlyForecast(
            weatherId = 0, hour = "12:00", main = Main(
                feelsLike = 0.0,
                grndLevel = 0,
                humidity = 0,
                pressure = 0,
                seaLevel = 0,
                temp = 0.0,
                tempMax = 0.0,
                tempMin = 0.0
            )
        )
    )
}