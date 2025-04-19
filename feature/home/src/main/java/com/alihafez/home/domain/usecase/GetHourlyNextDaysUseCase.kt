package com.alihafez.home.domain.usecase

import com.alihafez.core.domain.DataError
import com.alihafez.core.domain.Result
import com.alihafez.core.domain.map
import com.alihafez.core.presentation.common.formatDate
import com.alihafez.core.presentation.getCurrentDayFormatted
import com.alihafez.home.domain.model.ForecastModelItem
import com.alihafez.home.domain.model.asHourlyForecast
import com.alihafez.home.domain.repository.HomeRepository
import javax.inject.Inject

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
class GetHourlyNextDaysUseCase @Inject constructor(private val repository: HomeRepository) {
    suspend operator fun invoke(
        lat: Double,
        lon: Double
    ): Result<List<ForecastModelItem>, DataError.Remote> {
        return repository.getForecastData(lat, lon).map { model ->
            val groupedForecast = model.list
                .groupBy { it.dtTxt.formatDate() }
                .filter { it.key != getCurrentDayFormatted() }

            model.list
                .filter { it.dtTxt.formatDate() != getCurrentDayFormatted() }
                .distinctBy { it.dtTxt.formatDate() }
                .map {
                    it.copy(
                        hourlyForecast = groupedForecast.get(it.dtTxt.formatDate())
                            ?.map { it.asHourlyForecast() } ?: emptyList()
                    )
                }
        }
    }
}