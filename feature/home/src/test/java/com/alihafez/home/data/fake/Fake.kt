package com.alihafez.home.data.fake

import com.alihafez.core.data.remote.dto.*
import com.alihafez.core.domain.model.WeatherModel
import com.alihafez.home.domain.model.ForecastModelItem
import com.alihafez.home.domain.model.HourlyForecast

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
val fakeWeatherDto = WeatherDto(
    base = "stations",
    clouds = Clouds(all = 20),
    cod = 200,
    coord = Coord(lat = 37.7749, lon = -122.4194),
    dt = 1638316800,
    id = 5391959,
    main = Main(
        feels_like = 15.0,
        grnd_level = 1012,
        humidity = 75,
        pressure = 1015,
        sea_level = 1018,
        temp = 18.0,
        temp_max = 20.0,
        temp_min = 16.0
    ),
    name = "San Francisco",
    sys = Sys(
        country = "US",
        id = 5122,
        sunrise = 1638266400,
        sunset = 1638302400,
        type = 1
    ),
    timezone = -28800,
    visibility = 10000,
    weather = listOf(
        Weather(
            description = "clear sky",
            icon = "01d",
            id = 800,
            main = "Clear"
        )
    ),
    wind = Wind(
        deg = 350,
        gust = 5.0,
        speed = 3.6
    )
)

val fakeWeatherModel = WeatherModel(
    base = "stations",
    cod = 200,
    coord = com.alihafez.core.domain.model.Coord(lat = 37.7749, lon = -122.4194),
    id = 5391959,
    main = com.alihafez.core.domain.model.Main(
        feelsLike = 15.0,
        grndLevel = 1012,
        humidity = 75,
        pressure = 1015,
        seaLevel = 1018,
        temp = 18.0,
        tempMax = 20.0,
        tempMin = 16.0
    ),
    name = "San Francisco",
    timezone = -28800,
    visibility = 10000,
    weather = listOf(
       com.alihafez.core.domain.model. Weather(
            description = "clear sky",
            icon = "01d",
            id = 800,
            main = "Clear"
        )
    ),
    wind = com.alihafez.core.domain.model.Wind(
        deg = 350,
        gust = 5.0,
        speed = 3.6
    )
)

val fakeForecastDto = ForecastDto(
    cod = 200,
    message = 0,
    cnt = 5,
    list = listOf(
        ForecastItem(
            dt = 1638316800,
            main = Main(
                feels_like = 15.0,
                grnd_level = 1012,
                humidity = 75,
                pressure = 1015,
                sea_level = 1018,
                temp = 18.0,
                temp_max = 20.0,
                temp_min = 16.0
            ),
            visibility = 10000,
            weather = listOf(
                Weather(
                    description = "clear sky",
                    icon = "01d",
                    id = 800,
                    main = "Clear"
                )
            ),
            wind = Wind(
                deg = 350,
                gust = 5.0,
                speed = 3.6
            ),
            clouds = Clouds(all = 20),
            pop = 0.0,
            sys = ForecastSys(pod = "d"),
            dt_txt = "2025-02-10 12:00:00"
        ),
        ForecastItem(
            dt = 1638327600,
            main = Main(
                feels_like = 14.0,
                grnd_level = 1013,
                humidity = 80,
                pressure = 1016,
                sea_level = 1019,
                temp = 17.0,
                temp_max = 19.0,
                temp_min = 15.0
            ),
            visibility = 8000,
            weather = listOf(
                Weather(
                    description = "few clouds",
                    icon = "02d",
                    id = 801,
                    main = "Clouds"
                )
            ),
            wind = Wind(
                deg = 340,
                gust = 4.5,
                speed = 3.2
            ),
            clouds = Clouds(all = 40),
            pop = 0.1,
            sys = ForecastSys(pod = "d"),
            dt_txt = "2025-02-11 15:00:00"
        )
    )
)

val fakeForecastModelItem = ForecastModelItem(
    dt = 1638327600,
    dtTxt = "2025-02-10 15:00:00",
    main = com.alihafez.core.domain.model.Main(
        temp = 18.0,
        feelsLike = 15.0,
        tempMin = 16.0,
        tempMax = 20.0,
        pressure = 1015,
        seaLevel = 1018,
        grndLevel = 1012,
        humidity = 75
    ),
    weather = listOf(
        com.alihafez.core.domain.model.Weather(
            id = 800,
            main = "Clear",
            description = "clear sky",
            icon = "01d"
        )
    ),
    wind = com.alihafez.core.domain.model.Wind(
        speed = 3.6,
        deg = 350,
        gust = 5.0
    ),
    clouds = Clouds(all = 20),
    visibility = 10000,
    pop = 0.0,
    sys = ForecastSys(pod = "d"),
    hourlyForecast = listOf(
        HourlyForecast(
            weatherId = 800,
            main = com.alihafez.core.domain.model.Main(
                temp = 18.0,
                feelsLike = 15.0,
                tempMin = 16.0,
                tempMax = 20.0,
                pressure = 1015,
                seaLevel = 1018,
                grndLevel = 1012,
                humidity = 75
            ),
            hour = "12:00"
        )
    )
)

