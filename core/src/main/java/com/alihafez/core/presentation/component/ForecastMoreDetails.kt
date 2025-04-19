package com.alihafez.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alihafez.core.R
import com.alihafez.core.presentation.common.WeatherCondition
import com.alihafez.core.presentation.preview.PreviewLightDarkMode

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Composable
fun ForecastMoreDetails(modifier: Modifier = Modifier, condition: WeatherCondition) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = modifier.padding(horizontal = 2.dp, vertical = 2.dp)) {
            ConditionsLabelSection(
                modifier,
                com.alihafez.weatherformat.R.drawable.ic_wind,
                R.string.wind_label
            )
            ConditionsLabelSection(
                modifier,
                com.alihafez.weatherformat.R.drawable.ic_humidity,
                R.string.humidity_label
            )
            ConditionsLabelSection(
                modifier,
                com.alihafez.weatherformat.R.drawable.ic_visibility,
                R.string.visibility_label
            )
            ConditionsLabelSection(
                modifier,
                com.alihafez.weatherformat.R.drawable.ic_pressure,
                R.string.pressure_label
            )
        }
        Column(modifier = modifier.padding(horizontal = 4.dp, vertical = 4.dp)) {
            Text(
                text = "${condition.windSpeed}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = modifier.padding(horizontal = 4.dp, vertical = 2.dp)
            )
            Text(
                text = "${condition.humidity}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = modifier.padding(horizontal = 4.dp, vertical = 2.dp)
            )
            Text(
                text = "${condition.visibility}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = modifier.padding(horizontal = 4.dp, vertical = 2.dp)
            )
            Text(
                text = "${condition.pressure}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = modifier.padding(horizontal = 4.dp, vertical = 2.dp)
            )
        }
    }

}

@PreviewLightDarkMode
@Composable
private fun ForecastMoreDetailsPreview() {
    ForecastMoreDetails(condition = WeatherCondition())
}