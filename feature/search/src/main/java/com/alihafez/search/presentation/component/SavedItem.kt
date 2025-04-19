package com.alihafez.search.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alihafez.core.presentation.component.Subtitle
import com.alihafez.core.presentation.component.SubtitleSmall
import com.alihafez.core.R
import com.alihafez.core.presentation.component.ForecastMoreDetails
import com.alihafez.core.presentation.preview.PreviewLightDarkMode
import com.alihafez.search.presentation.SavedLocations

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Composable
fun SavedItem(
    modifier: Modifier = Modifier,
    savedLocations: SavedLocations
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(
                            R.string.format_temperature,
                            savedLocations.weatherUi.currentTemp
                        ),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    SubtitleSmall(
                        text = stringResource(
                            R.string.format_high_low_temperature,
                            savedLocations.weatherUi.maxTemp,
                            savedLocations.weatherUi.minTemp
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = savedLocations.weatherUi.weatherIcon),
                        contentDescription = savedLocations.weatherUi.weather,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(56.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Subtitle(
                        text = savedLocations.weatherUi.weather,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Subtitle(
                        text = savedLocations.weatherUi.locationName,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                ForecastMoreDetails(condition = savedLocations.weatherCondition)
            }
        }
    }
}

@PreviewLightDarkMode
@Composable
private fun SavedIemPreview() {
    SavedItem(savedLocations = SavedLocations())
}