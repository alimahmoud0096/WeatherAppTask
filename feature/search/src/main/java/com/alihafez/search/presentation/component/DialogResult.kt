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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.alihafez.core.R
import com.alihafez.core.domain.model.WeatherModel
import com.alihafez.core.presentation.component.ForecastMoreDetails
import com.alihafez.core.presentation.component.Subtitle
import com.alihafez.search.presentation.SearchUiState

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Composable
fun DialogResult(
    modifier: Modifier = Modifier,
    state: SearchUiState,
    onSavedLocationClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Subtitle(
                    text = state.cityName
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = state.weatherUi!!.weatherIcon),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(48.dp)
                        )

                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.format_temperature,
                                state.weatherUi!!.currentTemp
                            ),
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = stringResource(
                                id = R.string.format_high_low_temperature,
                                state.weatherUi.maxTemp, state.weatherUi.minTemp
                            ),
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
                Text(
                    text = state.weatherUi!!.weather,
                    style = MaterialTheme.typography.headlineLarge
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 2.dp)
                        .fillMaxWidth()
                ) {
                    ForecastMoreDetails(condition = state.weatherCondition)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    OutlinedButton(
                        onClick = onSavedLocationClick,
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Save Location")
                    }
                }
            }
        }
    }
}