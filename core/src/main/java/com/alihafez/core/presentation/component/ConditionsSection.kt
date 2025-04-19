package com.alihafez.core.presentation.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alihafez.core.R
import com.alihafez.core.presentation.getWeatherIconForPreview
import com.alihafez.core.presentation.preview.PreviewLightDarkMode

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Composable
fun ConditionsSection(
    modifier: Modifier = Modifier,
    conditionText: String,
    @StringRes conditionLabel: Int,
    @DrawableRes drawable: Int
) {
    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),

        ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)
                .padding(10.dp)
        ) {

            ConditionsLabelSection(modifier, drawable, conditionLabel)

            Text(
                text = conditionText,
                style = MaterialTheme.typography.headlineSmall,
                modifier = modifier.padding(horizontal = 4.dp, vertical = 4.dp)
            )
        }
    }
}

@PreviewLightDarkMode
@Composable
private fun ConditionsSectionPreview() {
    ConditionsSection(
        modifier = Modifier,
        conditionText = "Condition",
        conditionLabel = R.string.wind_label,
        drawable = getWeatherIconForPreview()
    )
}