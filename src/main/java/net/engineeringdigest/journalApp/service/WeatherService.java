package net.engineeringdigest.journalApp.service;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public String getWeather(String city){
        String finalAPI = appCache.APP_CACHE.get("weather_api").replace("CITY", city);
        // converting JSON to POJO is called deserialization
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        double tempInCelsius = response.getBody().getMain().getTemp() - 273.15;
        double humid = response.getBody().getMain().getHumidity();
        return "Today's Temperature in" + city + " is: " + (int) Math.round(tempInCelsius) + "°C & Humidity feels like: " + humid + "%";
    }
}
