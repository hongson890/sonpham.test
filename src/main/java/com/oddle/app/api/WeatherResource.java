package com.oddle.app.api;

import com.oddle.app.api.dto.WeatherLogDTO;
import com.oddle.app.gateway.dto.OpenWeatherMapInfoDTO;
import com.oddle.app.model.WeatherLog;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WeatherResource {

    /**
     * @api {GET} /api/weather/search
     * @apiName searchByParams
     * @apiGroup Weather
     * @apiDescription retrieve weather data from third party based on cities and save to weather_log table
     *
     * @apiParam {String} city name
     *
     * @apiSuccess weather log object
     *
     * @apiSuccessExample Success-Response:
     * {
     *     "coord": {
     *         "lon": 105,
     *         "lat": 21
     *     },
     *     "weather": [
     *         {
     *             "id": 803,
     *             "main": "Clouds",
     *             "description": "broken clouds",
     *             "icon": "04d"
     *         }
     *     ],
     *     "base": "stations",
     *     "main": {
     *         "temp": 293.15,
     *         "feels_like": 292.91,
     *         "temp_min": 293.15,
     *         "temp_max": 293.15,
     *         "pressure": 1022,
     *         "humidity": 77
     *     },
     *     "visibility": 6000,
     *     "wind": {
     *         "speed": 3.09,
     *         "deg": 60
     *     },
     *     "clouds": {
     *         "all": 75
     *     },
     *     "dt": 1611798093,
     *     "sys": {
     *         "type": 1,
     *         "id": 9308,
     *         "country": "VN",
     *         "sunrise": 1611790484,
     *         "sunset": 1611830648
     *     },
     *     "timezone": 25200,
     *     "id": 1581130,
     *     "name": "Hanoi",
     *     "cod": 200
     * }
     **/

    ResponseEntity<OpenWeatherMapInfoDTO> searchByParams(String inputParam);


    /**
     * @api {DELETE} /api/weather/weather-log/{weatherLogId}
     * @apiName deleteWeatherLog
     * @apiGroup Weather
     * @apiDescription delete weather_log in database
     *
     * @apiParam {Long} weatherLogId
     *
     * @apiSuccess weather log object
     **/
    ResponseEntity<Void> deleteWeatherLog(Long weatherLogId);


    /**
     * @api {GET} /api/weather/getListWeatherLog
     * @apiName getListWeatherLog
     * @apiGroup Weather
     * @apiDescription query from weather_log database and return data
     *
     * @apiParam {Long} weatherLogId
     *
     * @apiSuccess return list weather_log objects
     *
     * @apiSuccessExample Success-Response:
     * [
     *     {
     *         "id": 21,
     *         "city": "Phuket",
     *         "country": "TH",
     *         "updatedOn": 1611819506000,
     *         "temperature": 304.99,
     *         "windSpeed": 5.14,
     *         "humidity": 51,
     *         "weather": [
     *             {
     *                 "main": "Clouds",
     *                 "description": "few clouds",
     *                 "icon": "02d"
     *             }
     *         ]
     *     },
     *     {
     *         "id": 20,
     *         "city": "Russia",
     *         "country": "RU",
     *         "updatedOn": 1611819446000,
     *         "temperature": 245.83,
     *         "windSpeed": 4.07,
     *         "humidity": 86,
     *         "weather": [
     *             {
     *                 "main": "Clouds",
     *                 "description": "overcast clouds",
     *                 "icon": "04d"
     *             }
     *         ]
     *     }
     * ]
     *
     *
     **/
    ResponseEntity<List<WeatherLogDTO>> getListWeatherLog();

}

