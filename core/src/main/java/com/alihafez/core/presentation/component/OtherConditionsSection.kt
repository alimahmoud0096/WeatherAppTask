package com.alihafez.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alihafez.core.presentation.common.WeatherCondition
import com.alihafez.core.R
import com.alihafez.core.presentation.preview.PreviewLightDarkMode

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Composable
fun OtherConditionsSection(
    modifier: Modifier = Modifier,
    state: WeatherCondition
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth(),


    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            ConditionsSection(

                modifier = Modifier.weight(1f),
                conditionText = "${state.pressure}",
                conditionLabel = R.string.pressure_label,
                drawable = com.alihafez.weatherformat.R.drawable.ic_pressure
            )
            Spacer(modifier = Modifier.width(8.dp))
            ConditionsSection(
                modifier = Modifier.weight(1f),
                conditionText = "${state.windSpeed}",
                conditionLabel = R.string.wind_label,
                drawable = com.alihafez.weatherformat.R.drawable.ic_wind
            )
        }
        Spacer(
            modifier = Modifier
                .width(8.dp)
                .height(8.dp)
        )
        Row (horizontalArrangement = Arrangement.SpaceBetween){
            ConditionsSection(
                modifier = Modifier.weight(1f),
                conditionText = "${state.visibility}",
                conditionLabel = R.string.visibility_label,
                drawable = com.alihafez.weatherformat.R.drawable.ic_visibility
            )
            Spacer(modifier = Modifier.width(8.dp))
            ConditionsSection(
                modifier = Modifier.weight(1f),
                conditionText = "${state.humidity}",
                conditionLabel = R.string.humidity_label,
                drawable = com.alihafez.weatherformat.R.drawable.ic_humidity
            )
        }
    }
}

@PreviewLightDarkMode
@Composable
private fun OtherConditionsSectionPreview() {
    OtherConditionsSection(state = WeatherCondition())
}